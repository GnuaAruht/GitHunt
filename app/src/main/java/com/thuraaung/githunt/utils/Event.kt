package com.thuraaung.githunt.utils

class Event<T>(private val data : T) {

    private var isHandled = false

    fun getContentIfNotHandled() : T? {
        return if(isHandled) {
            null
        } else {
            isHandled = true
            data
        }
    }
}