package com.thuraaung.githunt.di.component

import android.content.Context
import com.thuraaung.githunt.GithuntApp
import com.thuraaung.githunt.di.builder.FragmentBuilder
import com.thuraaung.githunt.di.module.ApiModule
import com.thuraaung.githunt.di.module.DbModule
import com.thuraaung.githunt.di.module.ViewModelFactoryModule
import com.thuraaung.githunt.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ApiModule::class,
    DbModule::class,
    FragmentBuilder::class,
    ViewModelFactoryModule::class,
    ViewModelModule::class])
interface AppComponent : AndroidInjector<GithuntApp> {


    @Component.Builder
    interface AppBuilder {

        @BindsInstance
        fun create(context : Context) : AppBuilder

        fun build() : AppComponent
    }

    override fun inject(app : GithuntApp)
}