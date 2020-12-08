package com.lucky.task.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucky.task.utils.Event
import com.lucky.task.utils.coroutines.ContextProviders
import com.lucky.task.utils.network.ApplicationException
import com.lucky.task.utils.network.ErrorType
import com.lucky.task.utils.network.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

open class BaseViewModel(val contextProvider: ContextProviders): ViewModel() {
    val error = MutableLiveData<Event<Result.Error>>()
    val loading =
        MutableLiveData<Event<Result.Loading>>().apply { value = Event(Result.Loading(false)) }

    inline fun wrapBlockingOperation(
        showLoading: Boolean = true,
        crossinline function: suspend CoroutineScope.() -> Unit
    ): Job {
        loading.value = Event(Result.Loading(showLoading))
        return viewModelScope.launch(contextProvider.Main) {
            try {
                function()
            } catch (throwable: Throwable) {
                handelError(throwable)
                Timber.e(throwable)
            } finally {
                loading.value = Event(Result.Loading(false))
            }
        }
    }

    fun <T> handleResult(result: Result<T>, onSuccess: (Result.Success<T>) -> Unit) {
        when (result) {
            is Result.Success<T> -> {
                onSuccess(result)
            }
            is Result.Error -> {
                throw result.exception
            }
            else -> throw UnknownError()
        }
    }

    fun handelError(throwable: Throwable) {
        if (throwable is ApplicationException) {
            error.postValue(Event(Result.Error(throwable)))
            // todo handle different types of errors
            when (throwable.type) {
                ErrorType.Network.Unauthorized -> {}
                ErrorType.Network.ResourceNotFound -> {}
                ErrorType.Network.Unexpected -> {}
                ErrorType.Network.NoInternetConnection -> {}
                else -> {}
            }
        }
    }
}