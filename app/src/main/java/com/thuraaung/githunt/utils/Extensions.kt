package com.thuraaung.githunt.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.thuraaung.githunt.R


fun ImageView.loadImage(url : String) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.github)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .apply(RequestOptions.circleCropTransform())
        .into(this)
}

fun View.show() {
    visibility = View.VISIBLE
}


fun View.hide() {
    visibility = View.GONE
}