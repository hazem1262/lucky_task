package com.lucky.task.ui.offers.details

import androidx.lifecycle.MutableLiveData
import com.lucky.task.base.BaseViewModel
import com.lucky.task.data.remote.offers.OfferDetailsResponse
import com.lucky.task.data.remote.offers.OffersRepository
import com.lucky.task.utils.coroutines.ContextProviders
import javax.inject.Inject

class OfferDetailsViewModel @Inject constructor(contextProvider: ContextProviders, private val offersRepository: OffersRepository) : BaseViewModel(contextProvider){
    val offerDetailsLiveData: MutableLiveData<OfferDetailsResponse> = MutableLiveData()
    fun getOfferDetails(offerDetailsEndPoint:String){
        wrapBlockingOperation {
            handleResult(offersRepository.getOfferDetails(offerDetailsEndPoint)){
                offerDetailsLiveData.postValue(it.data)
            }
        }
    }
}