package com.thuraaung.githunt.repository.local

import com.thuraaung.githunt.db.TrendingRepoDb
import com.thuraaung.githunt.model.ModelLanguage
import com.thuraaung.githunt.model.ModelTrendingRepo
import com.thuraaung.githunt.utils.FlowLanguages
import com.thuraaung.githunt.utils.FlowTrendingRepos


class LocalDataSource(
    private val database : TrendingRepoDb
)  {

    fun insertRepos(repoList: List<ModelTrendingRepo>) {
        database.getDao().insertRepos(repoList)
    }

    fun getAllRepos(): FlowTrendingRepos {
        return database.getDao().getAllTrendingRepo()
    }

    fun getAllLanguage() : FlowLanguages {
        return database.getDao().getAllLanguage()
    }

    fun insertLanguages(languageList : List<ModelLanguage>) {
        database.getDao().insertLanguages(languageList)
    }

    fun searchLanguage(name : String = "%") : FlowLanguages {
        return database.getDao().searchLanguage(name)
    }

    fun isCacheAvailable() : Boolean {
        return database.getDao().getLanguageCount() > 0
    }
}