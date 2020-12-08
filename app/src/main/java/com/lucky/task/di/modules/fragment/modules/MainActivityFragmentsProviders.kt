package com.lucky.task.di.modules.fragment.modules

import com.lucky.task.ui.offers.details.OfferDetailsFragment
import com.lucky.task.ui.offers.list.OffersListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityFragmentsProviders {
    @ContributesAndroidInjector
    abstract fun contributeOffersListFragment(): OffersListFragment

    @ContributesAndroidInjector
    abstract fun contributeOfferDetailsFragment(): OfferDetailsFragment
}