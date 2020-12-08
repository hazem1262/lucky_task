package com.example.mvvmstarterproject.di.modules.fragment.modules

import com.example.mvvmstarterproject.ui.offers.details.OfferDetailsFragment
import com.example.mvvmstarterproject.ui.offers.list.OffersListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityFragmentsProviders {
    @ContributesAndroidInjector
    abstract fun contributeOffersListFragment(): OffersListFragment

    @ContributesAndroidInjector
    abstract fun contributeOfferDetailsFragment(): OfferDetailsFragment
}