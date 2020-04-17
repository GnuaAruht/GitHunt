package com.thuraaung.githunt.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thuraaung.githunt.model.ModelLanguage
import com.thuraaung.githunt.model.ModelTrendingRepo
import kotlinx.coroutines.flow.Flow

@Dao
interface TrendingRepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repoList : List<ModelTrendingRepo>)

    @Query("SELECT * FROM ${ModelTrendingRepo.TABLE_NAME} order by stars desc")
    fun getAllTrendingRepo() : Flow<List<ModelTrendingRepo>>

    @Query("SELECT * FROM ${ModelLanguage.TABLE_NAME} where name like :name")
    fun searchLanguage(name : String) : LiveData<List<ModelLanguage>>
}