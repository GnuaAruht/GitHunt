package com.thuraaung.githunt.repository

import androidx.lifecycle.LiveData
import com.thuraaung.githunt.*
import com.thuraaung.githunt.model.ModelLanguage
import com.thuraaung.githunt.model.ModelTrendingRepo
import com.thuraaung.githunt.repository.local.LocalDataSource
import com.thuraaung.githunt.repository.remote.RemoteDataSource
import com.thuraaung.githunt.utils.FlowLanguages
import com.thuraaung.githunt.utils.FlowTrendingRepos
import com.thuraaung.githunt.utils.ResponseLanguages
import com.thuraaung.githunt.utils.ResponseTrendingRepos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
class TrendingDataRepository (
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {


    fun getTrendingRepos()  = flow {

        emit(LoadingState<List<ModelTrendingRepo>>())

        try {

            val response = getRemoteRepos()
            val result = response.body()

            if(response.isSuccessful && result != null) {

                saveReposToLocal(result)

            } else {
                emit(ErrorState<List<ModelTrendingRepo>>("Unknown Error"))
            }

        } catch (e : Exception) {
            emit(ErrorState<List<ModelTrendingRepo>>("Cannot connect network"))
        }

        emitAll(
            getLocalRepos().map {
                if(!it.isNullOrEmpty())
                    SuccessState<List<ModelTrendingRepo>>(it)
                else
                    ErrorState<List<ModelTrendingRepo>>("Database empty")
            }
        )
    }.flowOn(Dispatchers.IO)

    fun getLanuages() = flow {

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

        emitAll(getLocalLanguages().map {
            if(!it.isNullOrEmpty())
                SuccessState<List<ModelLanguage>>(it)
            else
                ErrorState<List<ModelLanguage>>("Local Language empty")
        })


    }.flowOn(Dispatchers.IO)

    private suspend fun getRemoteRepos() : ResponseTrendingRepos {
        return remoteDataSource.getTrendingRepos()
    }

    private fun getLocalRepos() : FlowTrendingRepos {
        return localDataSource.getAllRepos()
    }

    private fun saveReposToLocal(repoList : List<ModelTrendingRepo>) {
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

    private fun getLocalLanguages() : FlowLanguages {
        return localDataSource.getAllLanguage()
    }
}