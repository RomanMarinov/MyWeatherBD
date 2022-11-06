package com.dev_marinov.myweather.data.remote.dto

import com.google.gson.annotations.SerializedName

data class GetLocationDTO(
    @SerializedName("location")
    val location: LocationDTO
)
{
//    fun mapToDomain() : GetLocation {
//        return GetLocation(
//            location = location
//        )
//    }
}
