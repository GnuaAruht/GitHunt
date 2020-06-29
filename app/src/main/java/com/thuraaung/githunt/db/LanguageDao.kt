package com.thuraaung.githunt.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thuraaung.githunt.model.ModelLanguage
import com.thuraaung.githunt.utils.FlowLanguages
import kotlinx.coroutines.flow.Flow

@Dao
interface LanguageDao  {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLanguages(language: List<ModelLanguage>)

    @Query("SELECT * FROM ${ModelLanguage.TABLE_NAME} order by name asc")
    fun getAllLanguage() : FlowLanguages

    @Query("SELECT * FROM ${ModelLanguage.TABLE_NAME} where name like :name")
    fun searchLanguage(name : String) : Flow<List<ModelLanguage>>

    @Query("SELECT COUNT(*) FROM ${ModelLanguage.TABLE_NAME}")
    fun getLanguageCount() : Int

}