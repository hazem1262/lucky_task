package com.lucky.task.di.modules

import android.content.Context
import com.lucky.task.BuildConfig
import com.lucky.task.utils.ConnectivityUtils
import com.lucky.task.utils.coroutines.ContextProviders
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class AppModule {

    @Provides
    fun provideContextProviders(): ContextProviders = ContextProviders()

    @Provides
    fun provideIsDebug() = BuildConfig.DEBUG

    @Provides
    @Singleton
    fun provideConnectivityUtils(context: Context): ConnectivityUtils = ConnectivityUtils(context)

}