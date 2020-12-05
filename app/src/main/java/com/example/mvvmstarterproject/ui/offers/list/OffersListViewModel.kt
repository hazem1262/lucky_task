package com.example.mvvmstarterproject.ui.offers.list

import androidx.lifecycle.MutableLiveData
import com.example.mvvmstarterproject.base.BaseViewModel
import com.example.mvvmstarterproject.data.remote.offers.Offers
import com.example.mvvmstarterproject.data.remote.offers.OffersRepository
import javax.inject.Inject

class OffersListViewModel @Inject constructor(private val offersRepository: OffersRepository) : BaseViewModel(){
    val offersLiveData: MutableLiveData<Offers> = MutableLiveData()
    fun getOffersList(){
        wrapBlockingOperation {
            handleResult(offersRepository.getListOfOffers()){
                offersLiveData.postValue(it.data)
            }
        }
    }
}