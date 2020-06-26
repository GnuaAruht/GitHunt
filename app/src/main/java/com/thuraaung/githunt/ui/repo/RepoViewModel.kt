package com.thuraaung.githunt.ui.repo

import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.*
import com.thuraaung.githunt.model.ModelRepo
import com.thuraaung.githunt.repository.DataRepository
import com.thuraaung.githunt.utils.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@ExperimentalCoroutinesApi
class RepoViewModel @Inject constructor(
    private val context: Context,
    private val repository: DataRepository
) : ViewModel() {

    private val _repoDataState = MutableLiveData<StateTrendingRepos>()

    private val repoList: LiveData<List<ModelRepo>>
        get() = Transformations.map(_repoDataState) {
            when (it) {
                is SuccessState -> it.data
                else -> emptyList()
            }
        }

    val isLoading: LiveData<Boolean>
        get() = Transformations.map(_repoDataState) {
            it is LoadingState
        }

    val isError: LiveData<Boolean>
        get() = Transformations.map(_repoDataState) {
            ( it is ErrorState ) || ( it !is LoadingState)
        }

    val isEmpty : LiveData<Boolean>
        get() = Transformations.map(repoList) {
            it.isNullOrEmpty()
        }

    var listUpdateCallback : (() -> Unit)? = null
    var itemClickCallback : ((ModelRepo) -> Unit)? = null
    private var observer: Observer<List<ModelRepo>>
    private val adapter = RepoAdapter().apply {

        repoClickListener = { repo ->
            itemClickCallback?.invoke(repo)
        }
        observer = Observer<List<ModelRepo>> { list ->
            updateItems(list)
            listUpdateCallback?.invoke()
        }
        repoList.observeForever(observer)
    }

    init {
        getTrendingRepos()
    }


    fun filterBySince(since_by : SinceBy) {
        if (context.savePreference(SINCE, since_by.value))
            getTrendingRepos()
    }

    fun filterLanguageBy(language_value: String) {
        if (context.savePreference(LANGUAGE, language_value))
            getTrendingRepos()
    }

    fun getRepoAdapter() : RepoAdapter {
        return adapter
    }

    fun getTrendingRepos() {

        viewModelScope.launch {

            val language = context.getPreferenceString(LANGUAGE,"")
            val sinceBy = context.getPreferenceString(SINCE,SinceBy.DAILY.value)
            repository.getTrendingRepos(language,sinceBy).collectLatest {
                _repoDataState.postValue(it)
            }

        }

    }

    override fun onCleared() {
        super.onCleared()
        repoList.removeObserver(observer)
    }

}