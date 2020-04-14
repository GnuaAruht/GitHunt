package com.thuraaung.githunt.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.thuraaung.githunt.*
import com.thuraaung.githunt.repository.TrendingDataRepository
import com.thuraaung.githunt.test.TestInjector

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

}
