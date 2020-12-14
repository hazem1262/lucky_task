package com.lucky.task.ui.offers.list

import androidx.lifecycle.MutableLiveData
import com.lucky.task.base.BaseViewModel
import com.lucky.task.data.remote.offers.OffersResponse.Section.Offer
import com.lucky.task.data.remote.offers.OffersRepository
import com.lucky.task.utils.coroutines.ContextProviders
import javax.inject.Inject

class OffersListViewModel @Inject constructor(contextProvider: ContextProviders, private val offersRepository: OffersRepository)
    : BaseViewModel(contextProvider){

    val offersLiveData: MutableLiveData<List<Offer>> = MutableLiveData()
    val offersPageTitle:MutableLiveData<String> = MutableLiveData()
    val noOffersLiveData:MutableLiveData<Boolean> = MutableLiveData()

    fun getOffersList(){
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
                noOffersLiveData.postValue(offers.size == 0)
            }
        }
    }
}