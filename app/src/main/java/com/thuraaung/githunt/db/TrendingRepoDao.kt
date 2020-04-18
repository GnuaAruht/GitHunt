package com.thuraaung.githunt.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thuraaung.githunt.model.ModelLanguage
import com.thuraaung.githunt.model.ModelTrendingRepo
import com.thuraaung.githunt.utils.FlowLanguages
import com.thuraaung.githunt.utils.FlowTrendingRepos
import kotlinx.coroutines.flow.Flow

@Dao
interface TrendingRepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepos(repoList : List<ModelTrendingRepo>)

    @Query("SELECT * FROM ${ModelTrendingRepo.TABLE_NAME} order by stars desc")
    fun getAllTrendingRepo() : FlowTrendingRepos

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLanguages(language: List<ModelLanguage>)

    @Query("SELECT * FROM ${ModelLanguage.TABLE_NAME} order by name asc")
    fun getAllLanguage() : FlowLanguages

    @Query("SELECT * FROM ${ModelLanguage.TABLE_NAME} where name like :name")
    fun searchLanguage(name : String) : Flow<List<ModelLanguage>>

    @Query("SELECT COUNT(*) FROM ${ModelLanguage.TABLE_NAME}")
    fun getLanguageCount() : Int
}