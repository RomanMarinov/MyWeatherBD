package com.dev_marinov.myweather.domain

data class Detail(
    val city: String,
    val time: String?,
    val temp_c: String?,
    val condition: Condition,
    val wind_kph: String?,
    val humidity: String?,
    val cloud: String?,
)
