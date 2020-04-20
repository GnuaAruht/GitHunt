package com.thuraaung.githunt.repository.local

import com.thuraaung.githunt.db.TrendingRepoDao
import com.thuraaung.githunt.db.TrendingRepoDb
import com.thuraaung.githunt.model.ModelLanguage
import com.thuraaung.githunt.model.ModelRepo
import com.thuraaung.githunt.utils.FlowLanguages
import com.thuraaung.githunt.utils.FlowTrendingRepos
import javax.inject.Inject


class LocalDataSource @Inject constructor(
    private val repoDao : TrendingRepoDao
)  {

    fun insertRepos(repoList: List<ModelRepo>) {
        repoDao.insertRepos(repoList)
    }

    fun getAllRepos(): FlowTrendingRepos {
        return repoDao.getAllTrendingRepo()
    }

    suspend fun deleteAllRepos() {
        repoDao.deleteAllRepo()
    }

    fun getAllLanguage() : FlowLanguages {
        return repoDao.getAllLanguage()
    }

    fun insertLanguages(languageList : List<ModelLanguage>) {
        repoDao.insertLanguages(languageList)
    }

    fun searchLanguage(name : String) : FlowLanguages {
        return repoDao.searchLanguage(name)
    }

    fun isCacheAvailable() : Boolean {
        return repoDao.getLanguageCount() > 0
    }
}