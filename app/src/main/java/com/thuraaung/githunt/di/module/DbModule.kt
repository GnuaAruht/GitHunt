package com.thuraaung.githunt.di.module

import android.content.Context
import com.thuraaung.githunt.db.TrendingRepoDao
import com.thuraaung.githunt.db.TrendingRepoDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context) : TrendingRepoDb {
        return TrendingRepoDb.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideDao(db : TrendingRepoDb) : TrendingRepoDao {
        return db.getDao()
    }
}