package com.example.mvvmstarterproject.data.remote.offers

import com.example.mvvmstarterproject.base.BaseRepository
import com.example.mvvmstarterproject.utils.ConnectivityUtils
import com.example.mvvmstarterproject.utils.network.ApplicationException
import com.example.mvvmstarterproject.utils.network.ErrorType
import com.example.mvvmstarterproject.utils.network.Result
import javax.inject.Inject

class OffersRepository @Inject constructor(connectivityUtils: ConnectivityUtils, private val offersService: OffersService): BaseRepository(connectivityUtils) {
    suspend fun getListOfOffers(): Result<OffersResponse> {
        return safeApiCall {
            offersService.getListOfOffers()
        }.let { result ->
            when (result) {
                is Result.Success -> Result.Success(result.data)
                is Result.Error -> result
                else -> Result.Error(ApplicationException(type = ErrorType.Unexpected))
            }
        }
    }

    suspend fun getOfferDetails(offerDetailsEndPoint:String): Result<OfferDetailsResponse> {
        return safeApiCall {
            offersService.getOfferDetails(offerDetailsEndPoint)
        }.let { result ->
            when (result) {
                is Result.Success -> Result.Success(result.data)
                is Result.Error -> result
                else -> Result.Error(ApplicationException(type = ErrorType.Unexpected))
            }
        }
    }
}