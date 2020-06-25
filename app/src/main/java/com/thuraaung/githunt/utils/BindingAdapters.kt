package com.thuraaung.githunt.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("adapter")
fun setAdapter(view : RecyclerView,adapter : RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("loading")
fun setLoading(view : View,isLoading : Boolean) {
    view.visibility = if(isLoading) View.GONE else View.VISIBLE
}

@BindingAdapter("drawableBackground")
fun setDrawableBackground(view : TextView,background : String?) {
    background?.let {
        view.setDrawableBackgroundColor(background)
    }
}

@BindingAdapter("loadImage")
fun loadImage(view : ImageView,url : String) {
    view.loadImage(url)
}