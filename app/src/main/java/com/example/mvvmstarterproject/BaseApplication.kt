package com.example.mvvmstarterproject

import android.app.Application
import com.example.mvvmstarterproject.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import dagger.android.HasActivityInjector


class BaseApplication : Application(), HasActivityInjector {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}