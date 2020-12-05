package com.example.mvvmstarterproject.ui

import com.example.mvvmstarterproject.base.BaseRepository
import com.example.mvvmstarterproject.data.remote.offers.OffersService
import com.example.mvvmstarterproject.data.remote.offers.User
import com.example.mvvmstarterproject.utils.ConnectivityUtils
import com.example.mvvmstarterproject.utils.network.ApplicationException
import com.example.mvvmstarterproject.utils.network.ErrorType
import com.example.mvvmstarterproject.utils.network.Result
import javax.inject.Inject

class TestRepositoryA @Inject constructor(connectivityUtils: ConnectivityUtils, private val offersService: OffersService): BaseRepository(connectivityUtils) {
    suspend fun getListOfUsers(): Result<List<User>> {
        return safeApiCall {
            offersService.getListOfUsers()
        }.let { result ->
            when (result) {
                is Result.Success -> Result.Success(result.data)
                is Result.Error -> result
                else -> Result.Error(ApplicationException(type = ErrorType.Unexpected))
            }
        }
    }
}