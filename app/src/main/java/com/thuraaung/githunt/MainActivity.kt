package com.thuraaung.githunt

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.thuraaung.githunt.repository.TrendingDataRepository
import com.thuraaung.githunt.test.TestInjector

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private val repository : TrendingDataRepository by lazy {
        TestInjector.getTrendingRepository(this)
    }

    private val viewModel : MainViewModel by viewModels { BaseViewModelFactory{ MainViewModel(repository) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        viewModel.reposList.observe(this, Observer {
            when(it) {
                is LoadingState -> {
                    Toast.makeText(this,"Loading ...",Toast.LENGTH_SHORT).show()
                }
                is ErrorState -> {
                    Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
                }
                is SuccessState -> {
                    Toast.makeText(this,"Data Size : ${it.data.size}",Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.getTrendingRepos()

    }

}
