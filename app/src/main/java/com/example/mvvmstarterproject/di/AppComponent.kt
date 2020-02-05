package com.example.mvvmstarterproject.di

import android.app.Application
import android.content.Context
import com.example.mvvmstarterproject.BaseApplication
import com.example.mvvmstarterproject.di.modules.ActivityModules
import com.example.mvvmstarterproject.di.modules.AppModule
import com.example.mvvmstarterproject.di.viewmodels.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ActivityModules::class,
    AppModule::class,
    ViewModelModule::class
])
interface AppComponent : AndroidInjector<BaseApplication> {
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application): Builder
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }
}