package com.thuraaung.githunt.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.thuraaung.githunt.base.BaseItem
import com.thuraaung.githunt.model.ModelTrendingRepo.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class ModelTrendingRepo(
    @PrimaryKey
    val url: String,
    val author: String,
    val avatar: String,
    val description: String,
    val forks: Int,
    val language: String? = null,
    val languageColor: String? = null,
    val name: String,
    val stars: Int
) : BaseItem {

    companion object {
        const val TABLE_NAME = "TrendingRepo"
    }
}