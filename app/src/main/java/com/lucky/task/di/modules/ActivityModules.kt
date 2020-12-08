package com.lucky.task.di.modules

import com.lucky.task.di.modules.fragment.modules.MainActivityFragmentsProviders
import com.lucky.task.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModules {
    @ContributesAndroidInjector(modules = [MainActivityFragmentsProviders::class])
    abstract fun contributeMainActivity(): MainActivity
}