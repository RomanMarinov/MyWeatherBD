package com.dev_marinov.myweather.data.remote

import com.dev_marinov.myweather.data.remote.dto.ResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private val default_key: String = "12cdbf51592a4e5a914112046221402"

interface DetailWeatherService {

//    https://api.weatherapi.com/v1/forecast.json?key=12cdbf51592a4e5a914112046221402&q=moscow

    @GET("forecast.json")
    suspend fun getDetailWeather(
        @Query("key") key: String = default_key,
        @Query("q") q: String,
    ): Response<ResponseModel>

}