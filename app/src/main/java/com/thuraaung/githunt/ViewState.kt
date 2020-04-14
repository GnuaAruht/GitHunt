package com.thuraaung.githunt



sealed class ViewState<T>

class LoadingState<T> : ViewState<T>()
class ErrorState<T>(val message : String) : ViewState<T>()
class SuccessState<T>(val data : T) : ViewState<T>()
