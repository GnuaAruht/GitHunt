package com.thuraaung.githunt.repository.local

import androidx.lifecycle.LiveData
import com.thuraaung.githunt.db.TrendingRepoDb
import com.thuraaung.githunt.model.ModelLanguage
import com.thuraaung.githunt.model.ModelTrendingRepo
import com.thuraaung.githunt.utils.FlowTrendingRepos


class LocalDataSource(
    private val database : TrendingRepoDb
)  {

    fun insertRepoList(repoList: List<ModelTrendingRepo>) {
        database.getDao().insert(repoList)
    }

    fun getAllRepoList(): FlowTrendingRepos {
        return database.getDao().getAllTrendingRepo()
    }

    fun searchLanguage(name : String) : LiveData<List<ModelLanguage>> {
        return database.getDao().searchLanguage(name)
    }
}