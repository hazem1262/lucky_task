package com.lucky.task.utils.coroutines

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/*
* wrap coroutin context for testing purposes
* https://proandroiddev.com/how-to-unit-test-code-with-coroutines-50c1640f6bef
* */
open class ContextProviders {
    open val Main: CoroutineContext = Dispatchers.Main
    open val IO: CoroutineContext = Dispatchers.IO

    companion object {
        @Volatile
        private var INSTANCE: ContextProviders? = null

        fun getInstance(): ContextProviders {
            return INSTANCE ?: synchronized(this) {
                ContextProviders()
            }.also {
                INSTANCE = it
            }
        }
    }
}