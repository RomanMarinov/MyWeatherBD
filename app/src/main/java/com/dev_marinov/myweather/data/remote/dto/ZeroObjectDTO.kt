package com.dev_marinov.myweather.data.remote.dto

import com.dev_marinov.myweather.data.remote.dto.DetailDTO
import com.google.gson.annotations.SerializedName

data class ZeroObjectDTO(
    @SerializedName("hour")
    val hour: List<DetailDTO>?
)
