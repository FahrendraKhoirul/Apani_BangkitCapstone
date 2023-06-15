package com.twelvenfive.apani.network.api

import com.twelvenfive.apani.network.response.*
import retrofit2.Response
import retrofit2.http.*

interface WeatherApiService {
    @Headers(
        "X-RapidAPI-Key: 68b649f7ddmsh8c5f396f1fe71c3p10a99bjsn60b410162d71",
        "X-RapidAPI-Host: weatherapi-com.p.rapidapi.com"
    )
    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("q") q: String,
    ): WeatherResponse

    @Headers(
        "X-RapidAPI-Key: 68b649f7ddmsh8c5f396f1fe71c3p10a99bjsn60b410162d71",
        "X-RapidAPI-Host: weatherapi-com.p.rapidapi.com"
    )
    @GET("forecast.json")
    suspend fun getForecastWeather(
        @Query("q") q: String,
        @Query("days") days: String
    ): Response<ForecastResponse>
}