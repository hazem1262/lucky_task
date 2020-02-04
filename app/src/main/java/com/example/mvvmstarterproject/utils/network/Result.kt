package com.example.mvvmstarterproject.utils.network


sealed class Result<out R> {
    data class Loading(val loading: Boolean) : Result<Nothing>()
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: ApplicationException) : Result<Nothing>()
}
