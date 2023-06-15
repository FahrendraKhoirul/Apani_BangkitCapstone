package com.twelvenfive.apani.network.response

import com.google.gson.annotations.SerializedName

data class ForecastResponse(

    @field:SerializedName("current")
    val current: Current? = null,

    @field:SerializedName("location")
    val location: Location? = null,

    @field:SerializedName("forecast")
    val forecast: Forecast? = null
)

data class CurrentForecast(

    @field:SerializedName("feelslike_c")
    val feelslikeC: Any? = null,

    @field:SerializedName("uv")
    val uv: Any? = null,

    @field:SerializedName("last_updated")
    val lastUpdated: String? = null,

    @field:SerializedName("feelslike_f")
    val feelslikeF: Any? = null,

    @field:SerializedName("wind_degree")
    val windDegree: Int? = null,

    @field:SerializedName("last_updated_epoch")
    val lastUpdatedEpoch: Int? = null,

    @field:SerializedName("is_day")
    val isDay: Int? = null,

    @field:SerializedName("precip_in")
    val precipIn: Any? = null,

    @field:SerializedName("wind_dir")
    val windDir: String? = null,

    @field:SerializedName("gust_mph")
    val gustMph: Any? = null,

    @field:SerializedName("temp_c")
    val tempC: Any? = null,

    @field:SerializedName("pressure_in")
    val pressureIn: Any? = null,

    @field:SerializedName("gust_kph")
    val gustKph: Any? = null,

    @field:SerializedName("temp_f")
    val tempF: Any? = null,

    @field:SerializedName("precip_mm")
    val precipMm: Any? = null,

    @field:SerializedName("cloud")
    val cloud: Int? = null,

    @field:SerializedName("wind_kph")
    val windKph: Any? = null,

    @field:SerializedName("condition")
    val condition: Condition? = null,

    @field:SerializedName("wind_mph")
    val windMph: Any? = null,

    @field:SerializedName("vis_km")
    val visKm: Any? = null,

    @field:SerializedName("humidity")
    val humidity: Int? = null,

    @field:SerializedName("pressure_mb")
    val pressureMb: Any? = null,

    @field:SerializedName("vis_miles")
    val visMiles: Any? = null
)

data class ForecastdayItem(

    @field:SerializedName("date")
    val date: String? = null,

    @field:SerializedName("astro")
    val astro: Astro? = null,

    @field:SerializedName("date_epoch")
    val dateEpoch: Int? = null,

    @field:SerializedName("hour")
    val hour: List<HourItem?>? = null,

    @field:SerializedName("day")
    val day: Day? = null
)

data class Day(

    @field:SerializedName("avgvis_km")
    val avgvisKm: Any? = null,

    @field:SerializedName("uv")
    val uv: Any? = null,

    @field:SerializedName("avgtemp_f")
    val avgtempF: Any? = null,

    @field:SerializedName("avgtemp_c")
    val avgtempC: Double? = null,

    @field:SerializedName("daily_chance_of_snow")
    val dailyChanceOfSnow: Int? = null,

    @field:SerializedName("maxtemp_c")
    val maxtempC: Any? = null,

    @field:SerializedName("maxtemp_f")
    val maxtempF: Any? = null,

    @field:SerializedName("mintemp_c")
    val mintempC: Any? = null,

    @field:SerializedName("avgvis_miles")
    val avgvisMiles: Any? = null,

    @field:SerializedName("daily_will_it_rain")
    val dailyWillItRain: Int? = null,

    @field:SerializedName("mintemp_f")
    val mintempF: Any? = null,

    @field:SerializedName("totalprecip_in")
    val totalprecipIn: Any? = null,

    @field:SerializedName("totalsnow_cm")
    val totalsnowCm: Any? = null,

    @field:SerializedName("avghumidity")
    val avghumidity: Double? = null,

    @field:SerializedName("condition")
    val condition: Condition? = null,

    @field:SerializedName("maxwind_kph")
    val maxwindKph: Any? = null,

    @field:SerializedName("maxwind_mph")
    val maxwindMph: Any? = null,

    @field:SerializedName("daily_chance_of_rain")
    val dailyChanceOfRain: Int? = null,

    @field:SerializedName("totalprecip_mm")
    val totalprecipMm: Any? = null,

    @field:SerializedName("daily_will_it_snow")
    val dailyWillItSnow: Int? = null
)

data class Astro(

    @field:SerializedName("moonset")
    val moonset: String? = null,

    @field:SerializedName("moon_illumination")
    val moonIllumination: String? = null,

    @field:SerializedName("sunrise")
    val sunrise: String? = null,

    @field:SerializedName("moon_phase")
    val moonPhase: String? = null,

    @field:SerializedName("sunset")
    val sunset: String? = null,

    @field:SerializedName("is_moon_up")
    val isMoonUp: Int? = null,

    @field:SerializedName("is_sun_up")
    val isSunUp: Int? = null,

    @field:SerializedName("moonrise")
    val moonrise: String? = null
)

data class ConditionForecase(

    @field:SerializedName("code")
    val code: Int? = null,

    @field:SerializedName("icon")
    val icon: String? = null,

    @field:SerializedName("text")
    val text: String? = null
)

data class Forecast(

    @field:SerializedName("forecastday")
    val forecastday: List<ForecastdayItem?>? = null
)

data class HourItem(

    @field:SerializedName("feelslike_c")
    val feelslikeC: Any? = null,

    @field:SerializedName("feelslike_f")
    val feelslikeF: Any? = null,

    @field:SerializedName("wind_degree")
    val windDegree: Int? = null,

    @field:SerializedName("windchill_f")
    val windchillF: Any? = null,

    @field:SerializedName("windchill_c")
    val windchillC: Any? = null,

    @field:SerializedName("temp_c")
    val tempC: Any? = null,

    @field:SerializedName("temp_f")
    val tempF: Any? = null,

    @field:SerializedName("cloud")
    val cloud: Int? = null,

    @field:SerializedName("wind_kph")
    val windKph: Any? = null,

    @field:SerializedName("wind_mph")
    val windMph: Any? = null,

    @field:SerializedName("humidity")
    val humidity: Int? = null,

    @field:SerializedName("dewpoint_f")
    val dewpointF: Any? = null,

    @field:SerializedName("will_it_rain")
    val willItRain: Int? = null,

    @field:SerializedName("uv")
    val uv: Any? = null,

    @field:SerializedName("heatindex_f")
    val heatindexF: Any? = null,

    @field:SerializedName("dewpoint_c")
    val dewpointC: Any? = null,

    @field:SerializedName("is_day")
    val isDay: Int? = null,

    @field:SerializedName("precip_in")
    val precipIn: Any? = null,

    @field:SerializedName("heatindex_c")
    val heatindexC: Any? = null,

    @field:SerializedName("wind_dir")
    val windDir: String? = null,

    @field:SerializedName("gust_mph")
    val gustMph: Any? = null,

    @field:SerializedName("pressure_in")
    val pressureIn: Any? = null,

    @field:SerializedName("chance_of_rain")
    val chanceOfRain: Int? = null,

    @field:SerializedName("gust_kph")
    val gustKph: Any? = null,

    @field:SerializedName("precip_mm")
    val precipMm: Any? = null,

    @field:SerializedName("condition")
    val condition: Condition? = null,

    @field:SerializedName("will_it_snow")
    val willItSnow: Int? = null,

    @field:SerializedName("vis_km")
    val visKm: Any? = null,

    @field:SerializedName("time_epoch")
    val timeEpoch: Int? = null,

    @field:SerializedName("time")
    val time: String? = null,

    @field:SerializedName("chance_of_snow")
    val chanceOfSnow: Int? = null,

    @field:SerializedName("pressure_mb")
    val pressureMb: Any? = null,

    @field:SerializedName("vis_miles")
    val visMiles: Any? = null
)

data class LocationForecast(

    @field:SerializedName("localtime")
    val localtime: String? = null,

    @field:SerializedName("country")
    val country: String? = null,

    @field:SerializedName("localtime_epoch")
    val localtimeEpoch: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("lon")
    val lon: Any? = null,

    @field:SerializedName("region")
    val region: String? = null,

    @field:SerializedName("lat")
    val lat: Any? = null,

    @field:SerializedName("tz_id")
    val tzId: String? = null
)
