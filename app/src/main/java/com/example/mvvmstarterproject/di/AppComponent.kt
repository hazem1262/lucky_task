package com.example.mvvmstarterproject.di

import android.app.Application
import com.example.mvvmstarterproject.BaseApplication
import com.example.mvvmstarterproject.di.modules.ActivityModules
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ActivityModules::class])
interface AppComponent : AndroidInjector<BaseApplication> {
    @Component.Builder()
    interface Builder{
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}