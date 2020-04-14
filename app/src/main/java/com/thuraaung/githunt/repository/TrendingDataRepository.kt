package com.thuraaung.githunt.repository

import com.thuraaung.githunt.*
import com.thuraaung.githunt.model.ModelTrendingRepo
import com.thuraaung.githunt.repository.local.LocalDataSource
import com.thuraaung.githunt.repository.remote.RemoteDataSource
import com.thuraaung.githunt.utils.ResponseTrendingRepos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import retrofit2.Response

@ExperimentalCoroutinesApi
class TrendingDataRepository (
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {


    fun getTrendingRepos()  = flow {

        emit(LoadingState<List<ModelTrendingRepo>>())

        try {

            val response = remoteDataSource.getTrendingRepos()
            val result = response.body()

            if(response.isSuccessful && result != null) {

                saveDataToLocal(result)

            } else {
                emit(ErrorState<List<ModelTrendingRepo>>("Unknown Error"))
            }

        } catch (e : Exception) {
            emit(ErrorState<List<ModelTrendingRepo>>("Cannot connect network"))
        }

        emitAll(
            getLocalData().map {
                if(!it.isNullOrEmpty())
                    SuccessState<List<ModelTrendingRepo>>(it)
                else
                    ErrorState<List<ModelTrendingRepo>>("Database empty")
            }
        )
    }.flowOn(Dispatchers.IO)

    private suspend fun getRemoteData() : ResponseTrendingRepos {
        return remoteDataSource.getTrendingRepos()
    }

    private fun getLocalData() : Flow<List<ModelTrendingRepo>> {
        return localDataSource.getAllRepoList()
    }

    private fun saveDataToLocal(repoList : List<ModelTrendingRepo>) {
        localDataSource.insertRepoList(repoList)
    }
}