package com.thuraaung.githunt.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.thuraaung.githunt.model.ModelTrendingRepo


@Database(entities = [ModelTrendingRepo::class],version = 1,exportSchema = false)
abstract class TrendingRepoDb : RoomDatabase() {

    abstract fun getDao() : TrendingRepoDao

    companion object {

        @Volatile
        private var INSTANCE : TrendingRepoDb? = null

        fun getInstance(context : Context) : TrendingRepoDb {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    TrendingRepoDb::class.java,
                    "trending_db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}