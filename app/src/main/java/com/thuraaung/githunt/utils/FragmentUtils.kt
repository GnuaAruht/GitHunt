package com.thuraaung.githunt.utils

import android.view.View
import androidx.navigation.fragment.FragmentNavigator

@Suppress("FunctionName")
fun FragmentNavigatorExtras(vararg sharedElements: Pair<View, String>) =
    FragmentNavigator.Extras.Builder().apply {
        sharedElements.forEach { (view, name) ->
            addSharedElement(view, name)
        }
    }.build()