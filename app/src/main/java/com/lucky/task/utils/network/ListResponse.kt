package com.lucky.task.utils.network

import com.google.gson.annotations.SerializedName

data class ListResponse<T>(
    @SerializedName("list") val list: List<T>
)


