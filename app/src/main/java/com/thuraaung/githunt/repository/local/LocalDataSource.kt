package com.thuraaung.githunt.repository.local

import com.thuraaung.githunt.db.TrendingRepoDb
import com.thuraaung.githunt.model.ModelTrendingRepo
import kotlinx.coroutines.flow.Flow


class LocalDataSource(
    private val database : TrendingRepoDb
)  {

    fun insertRepoList(repoList: List<ModelTrendingRepo>) {
        database.getDao().insert(repoList)
    }

    fun getAllRepoList(): Flow<List<ModelTrendingRepo>> {
        return database.getDao().getAllTrendingRepo()
    }
}