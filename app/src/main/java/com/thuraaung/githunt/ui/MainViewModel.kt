package com.thuraaung.githunt.ui

import android.content.Context
import androidx.lifecycle.*
import com.thuraaung.githunt.repository.TrendingDataRepository
import com.thuraaung.githunt.utils.FilterBy
import com.thuraaung.githunt.utils.ViewLanguages
import com.thuraaung.githunt.utils.ViewTrendingRepos
import com.thuraaung.githunt.utils.savePreference
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


    fun getTrendingRepos() {
        viewModelScope.launch {
            repository.getTrendingRepos("kotlin","daily").collect {
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