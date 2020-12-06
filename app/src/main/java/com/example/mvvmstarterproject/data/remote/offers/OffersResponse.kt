package com.example.mvvmstarterproject.data.remote.offers


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class OffersResponse(
    @SerializedName("sections")
    val sections: List<Section>,
    @SerializedName("title")
    val title: String?
){
    data class Section(
        @SerializedName("items")
        val offers: List<Offer>,
        @SerializedName("title")
        val title: String?
    ) {
        @Parcelize
        data class Offer(
            @SerializedName("brand")
            val brand: String?,
            @SerializedName("detailUrl")
            val detailUrl: String?,
            @SerializedName("favoriteCount")
            val favoriteCount: Int?,
            @SerializedName("imageUrl")
            val imageUrl: String?,
            @SerializedName("tags")
            val tags: String?,
            @SerializedName("title")
            val title: String?,
            var sectionTitle:String = "",
            var isSectionVisible:Boolean = false
        ):Parcelable
    }
}