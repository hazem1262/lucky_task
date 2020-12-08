package com.lucky.task.utils

import com.lucky.task.data.remote.offers.OffersResponse.Section.Offer

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