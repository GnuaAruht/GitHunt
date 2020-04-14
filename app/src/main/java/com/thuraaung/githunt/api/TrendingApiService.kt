package com.thuraaung.githunt.api

import com.thuraaung.githunt.utils.ResponseTrendingRepos
import retrofit2.http.GET

interface TrendingApiService {

    @GET("repositories")
    suspend fun getTrendingRepos() : ResponseTrendingRepos
}