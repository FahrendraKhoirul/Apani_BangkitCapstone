package com.twelvenfive.apani.network.data

import android.content.Context
import com.twelvenfive.apani.network.api.ApiConfig
import com.twelvenfive.apani.network.api.WeatherApiConfig

object Injection {
    fun provideRepository(context: Context): DataRepository {
        val apiService = ApiConfig.getApiServiceCC()
        val weatherApiService = WeatherApiConfig.getWeatherApiService()
        val preference = Preference(context)
        return DataRepository.getInstance(apiService, weatherApiService, preference, context)
    }
}