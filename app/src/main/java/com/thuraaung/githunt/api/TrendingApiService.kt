package com.thuraaung.githunt.api

import com.thuraaung.githunt.model.ModelTrendingRepo
import retrofit2.Response
import retrofit2.http.GET

interface TrendingApiService {

    @GET("repositories")
    suspend fun getTrendingRepos() : Response<List<ModelTrendingRepo>>
}