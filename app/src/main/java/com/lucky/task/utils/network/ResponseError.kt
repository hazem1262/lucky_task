package com.lucky.task.utils.network

import com.google.gson.annotations.SerializedName

data class ResponseError(
    @SerializedName("code") val code: Int = -1,
    @SerializedName("message") val message: String = ""
)

data class OrderIdExtra(
    @SerializedName("order_id") val orderId: Int = 0
)