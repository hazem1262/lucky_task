package com.example.mvvmstarterproject.ui.offers.list

import androidx.lifecycle.MutableLiveData
import com.example.mvvmstarterproject.base.BaseViewModel
import com.example.mvvmstarterproject.data.remote.offers.OffersResponse.Section.Offer
import com.example.mvvmstarterproject.data.remote.offers.OffersRepository
import javax.inject.Inject

class OffersListViewModel @Inject constructor(private val offersRepository: OffersRepository) : BaseViewModel(){
    val offersLiveData: MutableLiveData<List<Offer>> = MutableLiveData()
    val offersPageTitle:MutableLiveData<String> = MutableLiveData()

    init {
        getOffersList()
    }
    private fun getOffersList(){
        wrapBlockingOperation {
            handleResult(offersRepository.getListOfOffers()){ offersResponse ->
                val offers = arrayListOf<Offer>()
                offersResponse.data.sections.forEach {
                    section -> offers.addAll(
                        section.offers.apply {
                            forEachIndexed { index, offer ->
                                offer.sectionTitle = section.title?:""
                                offer.isSectionVisible = index == 0
                            }
                        }
                    )
                }
                offersLiveData.postValue(offers)
                offersPageTitle.postValue(offersResponse.data.title)
            }
        }
    }
}