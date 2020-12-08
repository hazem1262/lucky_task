package com.example.mvvmstarterproject.ui.offers.details

import androidx.lifecycle.MutableLiveData
import com.example.mvvmstarterproject.base.BaseViewModel
import com.example.mvvmstarterproject.data.remote.offers.OfferDetailsResponse
import com.example.mvvmstarterproject.data.remote.offers.OffersRepository
import com.example.mvvmstarterproject.utils.coroutines.ContextProviders
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