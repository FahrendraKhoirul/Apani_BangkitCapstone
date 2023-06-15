package com.twelvenfive.apani.ui.crops

import androidx.lifecycle.ViewModel
import com.twelvenfive.apani.network.data.DataRepository

class CropViewModel(private val dataRepository: DataRepository): ViewModel() {
    fun getForecastWeather() = dataRepository.getForecastWeather()
}