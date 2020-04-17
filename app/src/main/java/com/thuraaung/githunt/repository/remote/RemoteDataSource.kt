package com.thuraaung.githunt.repository.remote

import com.thuraaung.githunt.api.TrendingApiService
import com.thuraaung.githunt.utils.ResponseLanguages
import com.thuraaung.githunt.utils.ResponseTrendingRepos


class RemoteDataSource(
    private val apiService: TrendingApiService
) {

    suspend fun getTrendingRepos() : ResponseTrendingRepos {
        return apiService.getTrendingRepos()
    }

    suspend fun getLanguages() : ResponseLanguages {
        return apiService.getLanguages()
    }
}