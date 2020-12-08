package com.lucky.task.data.remote.offers


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface OffersService {

    @GET("offers")
    suspend fun getListOfOffers(): Response<OffersResponse>

    @GET
    suspend fun getOfferDetails(@Url offerDetailsEndPoint:String): Response<OfferDetailsResponse>
}