package com.dev_marinov.myweather.domain

interface ICitiesRepository {

    // чтобы напрямую из бд наблюдать
   // val details: Flow<List<Detail>>

    suspend fun getCities(): List<String>
    suspend fun getDetail(q: String) : List<Detail>

}