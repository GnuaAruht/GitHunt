package com.thuraaung.githunt.ui.language

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thuraaung.githunt.repository.DataRepository
import com.thuraaung.githunt.utils.ViewLanguages
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
class LanguageViewModel
    @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {

    private val _languages = MutableLiveData<ViewLanguages>()
    val languages : LiveData<ViewLanguages>
        get() = _languages

    fun getLanguages(name : String = "%") {
        viewModelScope.launch {
            dataRepository.getLanuages(name).collect {
                _languages.postValue(it)
            }
        }
    }
}