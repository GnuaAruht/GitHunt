package com.thuraaung.githunt.test

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.thuraaung.githunt.BuildConfig
import com.thuraaung.githunt.api.TrendingApiService
import com.thuraaung.githunt.db.TrendingRepoDb
import com.thuraaung.githunt.repository.TrendingDataRepository
import com.thuraaung.githunt.repository.local.LocalDataSource
import com.thuraaung.githunt.repository.remote.RemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


@ExperimentalCoroutinesApi
object TestInjector {

    private const val BASE_URL = "https://github-trending-api.now.sh/"

    fun getTrendingRepository(context: Context) : TrendingDataRepository {
        return TrendingDataRepository(getLocalDataSource(context), getRemoteDataSource())
    }

    private fun getRemoteDataSource() : RemoteDataSource {
        return RemoteDataSource(getTrendingApiService())
    }

    private fun getLocalDataSource(context : Context) : LocalDataSource {
        return LocalDataSource(TrendingRepoDb.getInstance(context).getDao())
    }

    private fun getTrendingApiService() : TrendingApiService {
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
            if (BuildConfig.DEBUG)
                level = HttpLoggingInterceptor.Level.BODY
            else
                level = HttpLoggingInterceptor.Level.HEADERS
        }
    }
}