package com.thuraaung.githunt.repository.remote

import com.thuraaung.githunt.api.TrendingApiService
import com.thuraaung.githunt.utils.ResponseLanguages
import com.thuraaung.githunt.utils.ResponseRepos


class RemoteDataSource(
    private val apiService: TrendingApiService
) {

    suspend fun getTrendingRepos() : ResponseRepos {
        return apiService.getTrendingRepos()
    }

    suspend fun getLanguages() : ResponseLanguages {
        return apiService.getLanguages()
    }
}