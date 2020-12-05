package com.example.mvvmstarterproject.di.modules.fragment.modules

import com.example.mvvmstarterproject.ui.TestFragmentA
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityFragmentsProviders {
    @ContributesAndroidInjector
    abstract fun contributeTestFragmentA(): TestFragmentA
}