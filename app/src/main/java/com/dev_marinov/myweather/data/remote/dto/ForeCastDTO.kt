package com.dev_marinov.myweather.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ForeCastDTO(
    @SerializedName("forecastday")
    val forecastday: List<ZeroObjectDTO>?
)
