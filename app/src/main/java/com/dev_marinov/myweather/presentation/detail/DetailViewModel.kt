package com.dev_marinov.myweather.presentation.detail

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import android.util.Log
import androidx.lifecycle.*
import com.dev_marinov.myweather.MyApplication
import com.dev_marinov.myweather.data.local.db.WeatherDao
import com.dev_marinov.myweather.domain.Detail
import com.dev_marinov.myweather.domain.ICitiesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    app: Application,
    private val weatherDao: WeatherDao,
    private val iCitiesRepository: ICitiesRepository
) : AndroidViewModel(app) { // AndroidViewModel для работы с контектом приложения

    var status: MutableStateFlow<Boolean> = MutableStateFlow(true)
    private val _viewState: MutableLiveData<String> = MutableLiveData()
    val viewState: LiveData<String> = _viewState

    private var _detailCity: MutableStateFlow<List<Detail>> = MutableStateFlow(listOf())
    var detailCity: Flow<List<Detail>> = _detailCity.asStateFlow()


    fun getDetail(city: String) {
        try {
            if (hasInternetConnection()) {
                // тут я беру данные деталей из сети если есть интернет
                viewModelScope.launch(Dispatchers.IO) {
                    val details = iCitiesRepository.getDetail(city)

                    _detailCity.value = details
                }
            } else { // тут я беру данные из рум если сети нет
                viewModelScope.launch(Dispatchers.IO) {
                    val details = weatherDao.getDetail(city).map { detailEntity ->
                        detailEntity.mapToDomain()
                    }
                    if (details.isNotEmpty()) {
                        _viewState.postValue("no internet connection\ndata retrieved from database")
                        _detailCity.value = details
                    } else {
                        _viewState.postValue("no internet connection\nno saved data in database")
                    }
                }
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> _viewState.postValue("network request error")
                else -> _viewState.postValue("conversion error")
            }
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<MyApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }
}