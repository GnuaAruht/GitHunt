package com.thuraaung.githunt.repository

import com.thuraaung.githunt.api.TrendingApiService
import com.thuraaung.githunt.db.LanguageDao
import com.thuraaung.githunt.model.ModelLanguage
import com.thuraaung.githunt.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class LanguageRepository @Inject constructor(
    private val languageDao: LanguageDao,
    private val trendingApiService: TrendingApiService
) {


    @ExperimentalCoroutinesApi
    fun getLanuages(name : String) = flow {

        emit(LoadingState<List<ModelLanguage>>())

        if(!isCacheAvailable()) {
            try {
                val response = getRemoteLanguages()
                val body = response.body()

                if (response.isSuccessful && body != null) {
                    saveLanguageToLocal(body)
                } else {
                    emit(
                        ErrorState<List<ModelLanguage>>("Language Request Failed")
                    )
                }

            } catch (e : Exception) {
                emit(
                    ErrorState<List<ModelLanguage>>("Language Request Failed")
                )
            }

        }

        emitAll(getLocalLanguages(name).map {
            if(!it.isNullOrEmpty())
                SuccessState<List<ModelLanguage>>(it)
            else
                ErrorState<List<ModelLanguage>>("Local Language empty")
        })


    }.flowOn(Dispatchers.IO)

    private suspend fun getRemoteLanguages() : ResponseLanguages {
        return trendingApiService.getLanguages()
    }

    private fun saveLanguageToLocal(languages : List<ModelLanguage>) {
        languageDao.insertLanguages(languages)
    }

    private fun isCacheAvailable() : Boolean {
        return languageDao.getLanguageCount() > 0
    }

    private fun getLocalLanguages(name : String) : FlowLanguages {
        return languageDao.searchLanguage(name)
    }
}