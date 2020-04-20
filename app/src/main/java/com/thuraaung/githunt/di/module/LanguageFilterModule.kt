package com.thuraaung.githunt.di.module

import com.thuraaung.githunt.ui.language.LanguageAdapter
import dagger.Module
import dagger.Provides

@Module
class LanguageFilterModule {

    @Provides
    fun provideLanguageAdapter() = LanguageAdapter()
}