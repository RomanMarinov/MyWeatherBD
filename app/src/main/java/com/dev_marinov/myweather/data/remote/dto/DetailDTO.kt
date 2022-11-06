package com.dev_marinov.myweather.data.remote.dto

import com.dev_marinov.myweather.domain.Detail
import com.google.gson.annotations.SerializedName

data class DetailDTO(
    @SerializedName("time")
    val time: String,
    @SerializedName("temp_c")
    val temp_c: String,

    @SerializedName("condition")
    val condition: ConditionDTO,

    @SerializedName("wind_kph")
    val wind_kph: String,
    @SerializedName("humidity")
    val humidity: String,
    @SerializedName("cloud")
    val cloud: String,

    ) {
    fun mapToDomain(city: String): Detail {
        return Detail(
            city = city,
            time = time,
            temp_c = temp_c,
            condition = condition.mapToDomain(),
            wind_kph = wind_kph,
            humidity = humidity,
            cloud = cloud

        )
    }
}
