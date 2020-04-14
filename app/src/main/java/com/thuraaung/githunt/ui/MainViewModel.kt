package com.thuraaung.githunt.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thuraaung.githunt.ViewState
import com.thuraaung.githunt.model.ModelTrendingRepo
import com.thuraaung.githunt.repository.TrendingDataRepository
import com.thuraaung.githunt.utils.ViewTrendingRepos
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
class MainViewModel(private val repository: TrendingDataRepository) : ViewModel() {


    private val _reposList = MutableLiveData<ViewTrendingRepos>()
    val reposList : LiveData<ViewTrendingRepos>
        get() = _reposList

    fun getTrendingRepos() {
        viewModelScope.launch {
            repository.getTrendingRepos().collect {
                _reposList.postValue(it)
            }
        }
    }
}