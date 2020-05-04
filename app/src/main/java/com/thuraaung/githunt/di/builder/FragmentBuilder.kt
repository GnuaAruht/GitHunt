package com.thuraaung.githunt.di.builder

import com.thuraaung.githunt.ui.language.LanguageFilterFragment
import com.thuraaung.githunt.ui.repo.TrendingReposFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import kotlinx.coroutines.ExperimentalCoroutinesApi


@Module
abstract class FragmentBuilder {

    @ExperimentalCoroutinesApi
    @ContributesAndroidInjector
    abstract fun bindRepoFragment() : TrendingReposFragment


    @ExperimentalCoroutinesApi
    @ContributesAndroidInjector
    abstract fun bindLanguageFragment() : LanguageFilterFragment
}