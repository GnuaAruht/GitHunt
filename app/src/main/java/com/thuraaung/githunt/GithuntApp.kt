package com.thuraaung.githunt

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.thuraaung.githunt.di.component.DaggerAppComponent
import com.thuraaung.githunt.utils.isNight
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject


class GithuntApp : Application() , HasAndroidInjector {

    @Inject
    lateinit var androidInjector : DispatchingAndroidInjector<Any>

    @ExperimentalCoroutinesApi
    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .create(baseContext)
            .build()
            .inject(this)

    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}