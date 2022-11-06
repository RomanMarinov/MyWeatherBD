package com.dev_marinov.myweather.data.remote.dto

import com.dev_marinov.myweather.data.remote.dto.DetailDTO
import com.google.gson.annotations.SerializedName

data class HourDTO(
    @SerializedName("list")
    val list: List<DetailDTO>?
)
