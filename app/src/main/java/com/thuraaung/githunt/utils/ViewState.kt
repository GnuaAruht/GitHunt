package com.thuraaung.githunt.utils



sealed class ViewState<T>

class LoadingState<T> : ViewState<T>()
data class ErrorState<T>(val message : String) : ViewState<T>()
data class SuccessState<T>(val data : T) : ViewState<T>()
