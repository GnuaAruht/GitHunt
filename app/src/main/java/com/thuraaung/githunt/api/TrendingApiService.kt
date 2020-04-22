package com.thuraaung.githunt.api

import com.thuraaung.githunt.utils.ResponseLanguages
import com.thuraaung.githunt.utils.ResponseRepos
import retrofit2.http.GET
import retrofit2.http.Query

interface TrendingApiService {

    @GET("repositories")
    suspend fun getTrendingRepos(
        @Query("language")
        language : String,
        @Query("since")
        since : String ) : ResponseRepos

    @GET("languages")
    suspend fun getLanguages() : ResponseLanguages
}