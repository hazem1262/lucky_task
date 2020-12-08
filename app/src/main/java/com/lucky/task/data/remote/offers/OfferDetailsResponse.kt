package com.lucky.task.data.remote.offers


import com.google.gson.annotations.SerializedName

data class OfferDetailsResponse(
    @SerializedName("brand")
    val brand: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("expiration")
    val expiration: String?,
    @SerializedName("favoriteCount")
    val favoriteCount: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("imageUrl")
    val imageUrl: String?,
    @SerializedName("price")
    val price: Price?,
    @SerializedName("redemptionsCap")
    val redemptionsCap: String?,
    @SerializedName("tags")
    val tags: String?,
    @SerializedName("title")
    val title: String?
) {
    data class Price(
        @SerializedName("new")
        val new: String?,
        @SerializedName("old")
        val old: String?
    )
}