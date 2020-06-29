package com.thuraaung.githunt.di.module

import androidx.lifecycle.ViewModelProvider
import com.thuraaung.githunt.base.ViewModelFactory
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface ViewModelFactoryModule {

    @Singleton
    @Binds
    fun providVMFactory(factory : ViewModelFactory) : ViewModelProvider.Factory
}