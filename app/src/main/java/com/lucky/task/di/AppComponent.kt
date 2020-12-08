package com.lucky.task.di

import android.app.Application
import android.content.Context
import com.lucky.task.application.BaseApplication
import com.lucky.task.di.modules.ActivityModules
import com.lucky.task.di.modules.AppModule
import com.lucky.task.di.viewmodels.ViewModelModule
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