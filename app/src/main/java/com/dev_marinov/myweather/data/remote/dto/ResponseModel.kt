package com.dev_marinov.myweather.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ResponseModel(
    @SerializedName("forecast")
    val forecast: ForeCastDTO
//    @SerializedName("location")
//    val location: LocationDTO
)
