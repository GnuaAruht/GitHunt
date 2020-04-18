package com.thuraaung.githunt.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.thuraaung.githunt.base.BaseItem
import com.thuraaung.githunt.model.ModelLanguage.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class ModelLanguage(
    @PrimaryKey
    val urlParam : String,
    val name : String
) : BaseItem {
    companion object {
        const val TABLE_NAME = "Language"
    }
}