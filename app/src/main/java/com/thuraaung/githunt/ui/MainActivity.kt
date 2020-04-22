package com.thuraaung.githunt.ui

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.thuraaung.githunt.R
import com.thuraaung.githunt.base.IActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity(), IActivity {

    private lateinit var navController : NavController
    private lateinit var appConfiguration : AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        navController = findNavController(R.id.nav_host_frag)
        appConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController,appConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        hideSoftKeyboard()
        return navController.navigateUp(appConfiguration) || super.onSupportNavigateUp()
    }

    override fun hideSoftKeyboard() {
        if(currentFocus != null) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager
                .hideSoftInputFromWindow(currentFocus!!.windowToken,0)
        }
    }

}
