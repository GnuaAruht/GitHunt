package com.thuraaung.githunt.ui

import android.content.Context
import androidx.lifecycle.*
import com.thuraaung.githunt.repository.TrendingDataRepository
import com.thuraaung.githunt.utils.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@ExperimentalCoroutinesApi
class MainViewModel @Inject constructor(
        private val context: Context,
        private val repository: TrendingDataRepository) : ViewModel() {

    private val _reposList = MutableLiveData<ViewTrendingRepos>()
    val reposList : LiveData<ViewTrendingRepos>
        get() = _reposList

    private val _languages = MutableLiveData<ViewLanguages>()
    val languages : LiveData<ViewLanguages>
        get() = _languages

    fun filterSinceBy(since_value : String) {
        if(context.savePreference(SINCE,since_value))
            getTrendingRepos()
    }

    fun filterLanguageBy(language_value : String) {
        if(context.savePreference(LANGUAGE,language_value))
            getTrendingRepos()
    }

    fun getTrendingRepos() {

        viewModelScope.launch {

            val prefLanguage = context.getPreferenceString(LANGUAGE,"")
            val prefFilterBy = context.getPreferenceString(SINCE, SINCE_DAILY)
            repository.getTrendingRepos(prefLanguage,prefFilterBy).collect {
                _reposList.postValue(it)
            }

        }
    }

    fun getLanguages(name : String = "%") {
        viewModelScope.launch {
            repository.getLanuages(name).collect {
                _languages.postValue(it)
            }
        }
    }
}