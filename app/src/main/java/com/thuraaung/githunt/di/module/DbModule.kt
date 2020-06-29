package com.thuraaung.githunt.di.module

import android.content.Context
import com.thuraaung.githunt.db.LanguageDao
import com.thuraaung.githunt.db.TrendingRepoDao
import com.thuraaung.githunt.db.TrendingRepoDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {

    @Provides
    fun provideDatabase(context: Context) : TrendingRepoDb {
        return TrendingRepoDb.getInstance(context)
    }


    @Provides
    fun provideTrendingRepoDao(db : TrendingRepoDb) : TrendingRepoDao {
        return db.trendingRepoDao()
    }


    @Provides
    fun provideLanguageDao(db : TrendingRepoDb) : LanguageDao {
        return db.languageDao()
    }
}