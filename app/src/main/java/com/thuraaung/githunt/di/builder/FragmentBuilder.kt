package com.thuraaung.githunt.di.builder

import com.thuraaung.githunt.di.module.LanguageFilterModule
import com.thuraaung.githunt.di.module.TrendingRepoModule
import com.thuraaung.githunt.ui.language.LanguageFilterFragment
import com.thuraaung.githunt.ui.repo.TrendingReposFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import kotlinx.coroutines.ExperimentalCoroutinesApi


@Module
abstract class FragmentBuilder {

    @ExperimentalCoroutinesApi
    @ContributesAndroidInjector(modules = [TrendingRepoModule::class])
    abstract fun bindRepoFragment() : TrendingReposFragment


    @ExperimentalCoroutinesApi
    @ContributesAndroidInjector(modules = [LanguageFilterModule::class])
    abstract fun bindLanguageFragment() : LanguageFilterFragment
}