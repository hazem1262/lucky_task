package com.example.mvvmstarterproject.di.modules

import com.example.mvvmstarterproject.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModules {
    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity
}