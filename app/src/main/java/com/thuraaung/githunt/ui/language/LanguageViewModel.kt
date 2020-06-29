package com.thuraaung.githunt.ui.language

import androidx.lifecycle.*
import com.thuraaung.githunt.model.ModelLanguage
import com.thuraaung.githunt.repository.LanguageRepository
import com.thuraaung.githunt.repository.RepoRepository
import com.thuraaung.githunt.utils.ErrorState
import com.thuraaung.githunt.utils.LoadingState
import com.thuraaung.githunt.utils.SuccessState
import com.thuraaung.githunt.utils.ViewLanguages
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
class LanguageViewModel
    @Inject constructor(private val languageRepository: LanguageRepository) : ViewModel() {

    private val _languageDataState = MutableLiveData<ViewLanguages>()

    private val languageList : LiveData<List<ModelLanguage>>
        get() = Transformations.map(_languageDataState) {
            when(it) {
                is SuccessState -> it.data
                else -> emptyList()
            }
        }

    val isError: LiveData<Boolean>
        get() = Transformations.map(_languageDataState) {
            ( it is ErrorState) || ( it !is LoadingState)
        }

    val isEmpty : LiveData<Boolean>
        get() = Transformations.map(languageList) {
            it.isNullOrEmpty()
        }

    var listUpdateCallback : (() -> Unit)? = null
    var itemClickCallback : ((ModelLanguage) -> Unit)? = null
    private var observer : Observer<List<ModelLanguage>>
    private val adapter = LanguageAdapter().apply {

        languageClickListener = { language ->
            itemClickCallback?.invoke(language)
        }
        observer = Observer {
            updateItems(it)
            listUpdateCallback?.invoke()
        }
        languageList.observeForever(observer)
    }

    init {
        getLanguages()
    }

    fun getLanguageAdapter() : LanguageAdapter {
        return adapter
    }


    fun getLanguages(name : String = "%") {
        viewModelScope.launch {
            languageRepository.getLanuages(name).collectLatest {
                _languageDataState.postValue(it)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        languageList.removeObserver(observer)
    }
}