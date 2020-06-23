package com.thuraaung.githunt.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.thuraaung.githunt.R
import com.thuraaung.githunt.utils.hide
import com.thuraaung.githunt.utils.hideSoftKeyboard
import com.thuraaung.githunt.utils.show
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var navController : NavController
    private lateinit var appConfiguration : AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        navController = findNavController(R.id.nav_host_frag)
        navController.addOnDestinationChangedListener { _, des, _ ->
            if (des.id == R.id.splashScreenFragment)
                toolbar.hide()
            else
                toolbar.show()
        }
        appConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController,appConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        hideSoftKeyboard()
        return navController.navigateUp(appConfiguration) || super.onSupportNavigateUp()
    }

}
