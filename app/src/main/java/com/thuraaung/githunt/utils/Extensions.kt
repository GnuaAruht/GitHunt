package com.thuraaung.githunt.utils

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.thuraaung.githunt.R
import java.lang.IllegalArgumentException


fun ImageView.loadImage(url : String) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.drawable_round_holder)
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

fun <T : RecyclerView.ViewHolder> T.listen(callback : (position : Int) -> Unit) : T {
    itemView.setOnClickListener { callback.invoke(adapterPosition) }
    return this
}

fun Context.savePreference(key : String, value : String) : Boolean {

    val prefValue = getPreferenceString(key,"NO_VALUE")
    return if(prefValue != value) {
        getSharedPreferences(APP_PREF, Context.MODE_PRIVATE)
            .edit()
            .putString(key,value)
            .apply()
        true
    } else {
        false
    }

}

fun Context.getPreferenceString(key : String,defaultValue : String) : String {
    return getSharedPreferences(APP_PREF,Context.MODE_PRIVATE)
        .getString(key,null) ?: defaultValue
}

fun TextView.setDrawableBackgroundColor(color: String) {
    compoundDrawables[0]?.let {
        val wrappedDrawable = DrawableCompat.wrap(it)
        try {
            DrawableCompat.setTint(wrappedDrawable, Color.parseColor(color))
        } catch (ignore: IllegalArgumentException) {
            Log.e(APP_NAME,"Cannot set background color language")
        }
    }
}