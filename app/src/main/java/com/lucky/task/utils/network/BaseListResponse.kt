package com.lucky.task.utils.network

import com.google.gson.annotations.SerializedName

data class BaseListResponse<T>(
    @SerializedName("data") val data: ListResponse<T>?,
    @SerializedName("error") val error: ResponseError?
)