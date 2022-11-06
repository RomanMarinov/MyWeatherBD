package com.dev_marinov.myweather.data.remote

import android.util.Log
import com.dev_marinov.myweather.data.local.db.WeatherDao
import com.dev_marinov.myweather.data.local.db.DetailEntity
import com.dev_marinov.myweather.domain.Detail
import com.dev_marinov.myweather.domain.ICitiesRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DetailWeatherRepository @Inject constructor(
    private val weatherDao: WeatherDao,
    private val service: DetailWeatherService
) : ICitiesRepository {

    //// чтобы напрямую из бд наблюдать
//    override val details: Flow<List<Detail>> = weatherDao.getAll().map { listDetailEntity ->
//        listDetailEntity.map {
//            it.mapToDomain()
//        }
//    }

    override suspend fun getCities(): List<String> {
        return listOf(
            "Moscow", "Kazan", "Vologda", "Vladimir", "Ivanovo", "Omsk", "Saratov", "Tyumen",
            "Chelyabinsk", "Yaroslavl", "Tambov", "Penza", "Novosibirsk", "Magadan", "Kemerovo"
        )
    }

    override suspend fun getDetail(q: String): List<Detail> {
        val response = service.getDetailWeather(q = q)

        val details = response.body()?.forecast?.forecastday?.let { forecastDay ->
            forecastDay[0].hour?.let { listDetailDto ->
                listDetailDto.map { detailDTO ->
                    detailDTO.mapToDomain(city = q)
                }
            }
        }

        saveDetail(detail = details ?: emptyList())

        return details ?: emptyList()
    }

    private fun saveDetail(detail: List<Detail>) {
        val entities = detail.map {
            DetailEntity.mapFromDomainToData(it)
        }
        Log.d("333", "my entities =$entities")
        weatherDao.insert(detail = entities)
    }

}