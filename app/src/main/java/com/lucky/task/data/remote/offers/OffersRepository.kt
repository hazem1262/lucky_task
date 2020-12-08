package com.lucky.task.data.remote.offers

import com.lucky.task.base.BaseRepository
import com.lucky.task.utils.ConnectivityUtils
import com.lucky.task.utils.coroutines.ContextProviders
import com.lucky.task.utils.network.ApplicationException
import com.lucky.task.utils.network.ErrorType
import com.lucky.task.utils.network.Result
import javax.inject.Inject

class OffersRepository @Inject constructor(contextProvider: ContextProviders, connectivityUtils: ConnectivityUtils, private val offersService: OffersService): BaseRepository(contextProvider, connectivityUtils) {
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