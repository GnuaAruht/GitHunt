package com.thuraaung.githunt

import com.thuraaung.githunt.model.ModelTrendingRepo
import com.thuraaung.githunt.model.ResponseTrendingRepo
import java.util.*


object DataMapper {

    fun responseToModel(apiResponse : List<ResponseTrendingRepo>) : List<ModelTrendingRepo> {
        return apiResponse.map {
            ModelTrendingRepo(
                id = UUID.randomUUID().toString(),
                author = it.author,
                avatar = it.avatar,
                description = it.description,
                forks = it.forks,
                language = it.language,
                languageColor = it.languageColor,
                name = it.name,
                stars = it.stars,
                url = it.url
            )
        }
    }
}