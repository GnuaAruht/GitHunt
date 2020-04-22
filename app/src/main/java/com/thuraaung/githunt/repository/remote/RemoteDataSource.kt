package com.thuraaung.githunt.repository.remote

import com.thuraaung.githunt.api.TrendingApiService
import com.thuraaung.githunt.utils.ResponseLanguages
import com.thuraaung.githunt.utils.ResponseRepos
import javax.inject.Inject


class RemoteDataSource @Inject constructor(
    private val apiService: TrendingApiService
) {

    suspend fun getTrendingRepos(language : String,filterBy : String) : ResponseRepos {
        return apiService.getTrendingRepos(language,filterBy)
    }

    suspend fun getLanguages() : ResponseLanguages {
        return apiService.getLanguages()
    }
}