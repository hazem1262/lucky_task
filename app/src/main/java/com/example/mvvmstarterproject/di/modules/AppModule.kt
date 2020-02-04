package com.example.mvvmstarterproject.di.modules

import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideTestString() = "test string"
}