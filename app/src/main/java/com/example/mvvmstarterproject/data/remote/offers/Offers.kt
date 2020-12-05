package com.example.mvvmstarterproject.data.remote.offers


import com.google.gson.annotations.SerializedName

data class Offers(
    @SerializedName("sections")
    val sections: List<Section?>?,
    @SerializedName("title")
    val title: String?
) {
    data class Section(
        @SerializedName("items")
        val items: List<Item?>?,
        @SerializedName("title")
        val title: String?
    ) {
        data class Item(
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
            val title: String?
        )
    }
}