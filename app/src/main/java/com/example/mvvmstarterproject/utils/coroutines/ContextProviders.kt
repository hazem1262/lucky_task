package com.ibtikar.mvvm_starter_koin_coroutines.utils.coroutines

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

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