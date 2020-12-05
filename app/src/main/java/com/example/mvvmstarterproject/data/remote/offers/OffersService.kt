package com.example.mvvmstarterproject.data.remote.offers


import retrofit2.Response
import retrofit2.http.GET

interface OffersService {

    @GET("offers")
    suspend fun getListOfOffers(): Response<OffersResponse>
}