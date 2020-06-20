package com.thuraaung.githunt.utils



sealed class DataState<out T>

class LoadingState<T> : DataState<T>()
data class ErrorState<T>(val message : String) : DataState<T>()
data class SuccessState<T>(val data : T) : DataState<T>()
