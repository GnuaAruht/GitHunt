package com.thuraaung.githunt.repository

import com.thuraaung.githunt.api.TrendingApiService
import com.thuraaung.githunt.db.TrendingRepoDao
import com.thuraaung.githunt.model.ModelRepo
import com.thuraaung.githunt.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
class RepoRepository @Inject constructor(
    private val repoDao: TrendingRepoDao,
    private val apiService: TrendingApiService
) {


    fun getTrendingRepos(language : String,filterBy : String) : Flow<DataState<List<ModelRepo>>> = flow {

        emit(LoadingState<List<ModelRepo>>())

        try {
            val response = getRemoteRepos(language,filterBy)
            val result = response.body()
            if(response.isSuccessful && result != null) {
                updateRepos(result)
            }

        } catch (e : Exception) {
            e.printStackTrace()
        }

        emitAll(
            getLocalRepos().map {
                if(!it.isNullOrEmpty())
                    SuccessState<List<ModelRepo>>(it)
                else
                    ErrorState<List<ModelRepo>>("Database empty")
            }
        )
    }.flowOn(Dispatchers.IO)


    private suspend fun getRemoteRepos(language : String,filterBy: String) : ResponseRepos {
        return apiService.getTrendingRepos(language,filterBy)
    }

    private fun getLocalRepos() : FlowTrendingRepos {
        return repoDao.getAllTrendingRepo()
    }

    private fun updateRepos(repos : List<ModelRepo>) {
        repoDao.updateRepos(repos)
    }

}