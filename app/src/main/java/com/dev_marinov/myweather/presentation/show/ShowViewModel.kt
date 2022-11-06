package com.dev_marinov.myweather.presentation.show

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev_marinov.myweather.SingleLiveEvent
import com.dev_marinov.myweather.domain.ICitiesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowViewModel @Inject constructor(
    private val iCitiesRepository: ICitiesRepository
) : ViewModel() {

    private val _cities: MutableLiveData<List<String>> = MutableLiveData()
    var cities: LiveData<List<String>> = _cities

    private val _uploadData = SingleLiveEvent<String>()
    val uploadData: SingleLiveEvent<String> = _uploadData

    init {
        getCities()
    }

    fun onClick(id: String){
        _uploadData.postValue(id)
    }

    private fun getCities() {
        viewModelScope.launch(Dispatchers.IO) {
            iCitiesRepository.getCities().let {
                _cities.postValue(it)
            }
        }
    }
}