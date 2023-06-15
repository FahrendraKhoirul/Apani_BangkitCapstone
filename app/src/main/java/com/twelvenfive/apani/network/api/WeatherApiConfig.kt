package com.twelvenfive.apani.network.api

import com.twelvenfive.apani.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherApiConfig {
    companion object{
        private val loggingInterceptor = if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        fun getWeatherApiService(): WeatherApiService{
            val weatherClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://weatherapi-com.p.rapidapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(weatherClient)
                .build()
            return retrofit.create(WeatherApiService::class.java)
        }
    }
}