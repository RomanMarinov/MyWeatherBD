package com.dev_marinov.myweather.data.remote.dto

import com.dev_marinov.myweather.domain.Location
import com.google.gson.annotations.SerializedName

data class LocationDTO(
    @SerializedName("name")
    val name: String
)
{
    fun mapToDomain() :Location {
        return Location(
            name = name
        )
    }
}
