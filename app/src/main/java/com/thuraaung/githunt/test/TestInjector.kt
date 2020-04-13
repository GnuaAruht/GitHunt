package com.thuraaung.githunt.test

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.thuraaung.githunt.api.TrendingApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


object TestInjector {

    private const val BASE_URL = "https://github-trending-api.now.sh/"

    fun getTrendingApiService() : TrendingApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getOkhttpClient())
            .addConverterFactory(
                MoshiConverterFactory.create(
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            ))
            .build()
            .create(TrendingApiService::class.java)
    }

    private fun getOkhttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                getLoggingInterceptor()
            )
            .connectTimeout(5,TimeUnit.SECONDS)
            .readTimeout(5,TimeUnit.SECONDS)
            .build()
    }

    private fun getLoggingInterceptor() : HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}