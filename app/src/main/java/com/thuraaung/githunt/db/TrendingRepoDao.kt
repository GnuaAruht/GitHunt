package com.thuraaung.githunt.db

import androidx.room.*
import com.thuraaung.githunt.model.ModelLanguage
import com.thuraaung.githunt.model.ModelRepo
import com.thuraaung.githunt.utils.FlowLanguages
import com.thuraaung.githunt.utils.FlowTrendingRepos
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TrendingRepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertRepos(repoList : List<ModelRepo>)

    @Query("DELETE FROM ${ModelRepo.TABLE_NAME}")
    abstract fun deleteAllRepo()

    @Transaction
    open fun updateRepos(repoList : List<ModelRepo>) {
        deleteAllRepo()
        insertRepos(repoList)
    }

    @Query("SELECT * FROM ${ModelRepo.TABLE_NAME} order by stars desc")
    abstract fun getAllTrendingRepo() : FlowTrendingRepos


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertLanguages(language: List<ModelLanguage>)

    @Query("SELECT * FROM ${ModelLanguage.TABLE_NAME} order by name asc")
    abstract fun getAllLanguage() : FlowLanguages

    @Query("SELECT * FROM ${ModelLanguage.TABLE_NAME} where name like :name")
    abstract fun searchLanguage(name : String) : Flow<List<ModelLanguage>>

    @Query("SELECT COUNT(*) FROM ${ModelLanguage.TABLE_NAME}")
    abstract fun getLanguageCount() : Int
}