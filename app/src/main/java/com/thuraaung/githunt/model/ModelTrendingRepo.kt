package com.thuraaung.githunt.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.thuraaung.githunt.model.ModelTrendingRepo.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class ModelTrendingRepo(
    @PrimaryKey
    val id : String,
    @ColumnInfo(name = "author")
    val author: String,
    @ColumnInfo(name = "avatar")
    val avatar: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "forks")
    val forks: Int,
    @ColumnInfo(name = "language")
    val language: String? = null,
    @ColumnInfo(name = "languageColor")
    val languageColor: String? = null,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "stars")
    val stars: Int,
    @ColumnInfo(name = "url")
    val url: String
) {

    companion object {
        const val TABLE_NAME = "TrendingRepo"
    }
}