package com.thuraaung.githunt.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.thuraaung.githunt.model.ModelRepo.Companion.TABLE_NAME
import kotlinx.android.parcel.Parcelize

@Entity(tableName = TABLE_NAME)
@Parcelize
data class ModelRepo(
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
) : Parcelable {

    companion object {
        const val TABLE_NAME = "TrendingRepo"
    }
}