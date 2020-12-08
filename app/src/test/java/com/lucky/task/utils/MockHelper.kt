package com.example.mvvmstarterproject.utils

import com.example.mvvmstarterproject.data.remote.offers.OffersResponse.Section.Offer

/*using mock() function results in exception,
so used this work around for mocking offers*/
fun createRandomOffer(): Offer {
    return Offer(
        brand = "",
        title = "",
        detailUrl = "",
        favoriteCount = 0,
        sectionTitle = "",
        tags = "",
        imageUrl = ""
    )
}