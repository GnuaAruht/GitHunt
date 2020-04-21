package com.thuraaung.githunt.repository

import com.thuraaung.githunt.ErrorState
import com.thuraaung.githunt.LoadingState
import com.thuraaung.githunt.SuccessState
import com.thuraaung.githunt.model.ModelLanguage
import com.thuraaung.githunt.model.ModelRepo
import com.thuraaung.githunt.repository.local.LocalDataSource
import com.thuraaung.githunt.repository.remote.RemoteDataSource
import com.thuraaung.githunt.utils.FlowLanguages
import com.thuraaung.githunt.utils.FlowTrendingRepos
import com.thuraaung.githunt.utils.ResponseLanguages
import com.thuraaung.githunt.utils.ResponseRepos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ExperimentalCoroutinesApi
class TrendingDataRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {


    fun getTrendingRepos()  = flow {

        emit(LoadingState<List<ModelRepo>>())

        try {

            val response = getRemoteRepos()
            val result = response.body()

            if(response.isSuccessful && result != null) {

                clearOldData()
                saveReposToLocal(result)

            } else {
                emit(ErrorState<List<ModelRepo>>("Unknown Error"))
            }

        } catch (e : Exception) {
            emit(ErrorState<List<ModelRepo>>("Cannot connect network"))
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

    fun getLanuages(name : String) = flow {

        emit(LoadingState<List<ModelLanguage>>())

        if(!isCacheAvailable()) {
            try {
                val response = getRemoteLanguages()
                val body = response.body()

                if (response.isSuccessful && body != null) {
                    saveLanguageToLocal(body)
                } else {
                    emit(ErrorState<List<ModelLanguage>>("Language Request Failed"))
                }

            } catch (e : Exception) {
                emit(ErrorState<List<ModelLanguage>>("Language Request Failed"))
            }

        }

        emitAll(getLocalLanguages(name).map {
            if(!it.isNullOrEmpty())
                SuccessState<List<ModelLanguage>>(it)
            else
                ErrorState<List<ModelLanguage>>("Local Language empty")
        })


    }.flowOn(Dispatchers.IO)

    private suspend fun getRemoteRepos() : ResponseRepos {
        return remoteDataSource.getTrendingRepos()
    }

    private fun getLocalRepos() : FlowTrendingRepos {
        return localDataSource.getAllRepos()
    }

    private suspend fun clearOldData() {
        localDataSource.deleteAllRepos()
    }

    private fun saveReposToLocal(repoList : List<ModelRepo>) {
        localDataSource.insertRepos(repoList)
    }

    private suspend fun getRemoteLanguages() : ResponseLanguages {
        return remoteDataSource.getLanguages()
    }

    private fun saveLanguageToLocal(languages : List<ModelLanguage>) {
        localDataSource.insertLanguages(languages)
    }

    private fun isCacheAvailable() : Boolean {
        return localDataSource.isCacheAvailable()
    }

    private fun getLocalLanguages(name : String) : FlowLanguages {
        return localDataSource.searchLanguage(name)
    }
}