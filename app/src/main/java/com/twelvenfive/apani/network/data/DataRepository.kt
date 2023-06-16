package com.twelvenfive.apani.network.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.twelvenfive.apani.R
import com.twelvenfive.apani.network.api.ApiService
import com.twelvenfive.apani.network.api.WeatherApiService
import com.twelvenfive.apani.network.response.*


class DataRepository(private val apiService: ApiService, private val weatherApiService: WeatherApiService, private val preference: Preference, private val context: Context) {
    companion object{
        @Volatile
        private var instance: DataRepository? = null
        fun getInstance(
            apiService: ApiService,
            weatherApiService: WeatherApiService,
            preference: Preference,
            context: Context
        ): DataRepository =
            instance ?: synchronized(this){
                instance ?: DataRepository(apiService, weatherApiService, preference, context)
            }.also { instance = it }
    }

    fun register(username: String, email: String, pass: String): LiveData<Result<RegisterResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.register(username, email, pass)
            if (response.email.isNotEmpty()){
                emit(Result.Success(response))
            }else{

            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun login(email: String, pass: String): LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.login(email, pass)
            if (response.loginResult?.email.isNullOrEmpty()){
                emit(Result.Error(response.message!!))
            }else{
                emit(Result.Success(response))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getCurrentWeather(): LiveData<Result<WeatherResponse>> = liveData {
        emit(Result.Loading)
        val location = preference.getLocation()
        try {
            val response = weatherApiService.getCurrentWeather(location.coordinate as String)
            if(response.current?.tempC != null){
                emit(Result.Success(response))
            }
            else{
                emit(Result.Error("Something wrong"))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getForecastWeather(): LiveData<Result<List<ForecastdayItem>>> = liveData {
        emit(Result.Loading)
        val location = preference.getLocation()
        val days = "3"
        try {
            val response = weatherApiService.getForecastWeather(location.city as String, days)
            if (response.isSuccessful) {
                val weather = response.body()?.forecast?.forecastday
                if (weather.isNullOrEmpty()) {
                    emit(Result.Error("No weather found"))
                } else {
                    emit(Result.Success(weather as List<ForecastdayItem>))
                }
            } else {
                emit(Result.Error("API call failed with code ${response.code()}"))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getAllArticles(): LiveData<Result<List<ArticleListItem>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getAllArticles()
            if (response.isSuccessful) {
                val articles = response.body()?.articleList
                if (articles.isNullOrEmpty()) {
                    emit(Result.Error("No articles found"))
                } else {
                    emit(Result.Success(articles))
                }
            } else {
                emit(Result.Error("API call failed with code ${response.code()}"))
            }
        } catch (e: Exception) {
            Log.d("DataRepository", "listData: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getAllProjects(): LiveData<Result<List<ListProjectItem>>> = liveData {
        emit(Result.Loading)
        val email = preference.getData().email
        Log.d("PROJECTS ==========", preference.getData().email.toString())
        try {
            val response = apiService.getAllProjects(email = email.toString())
            if (response.isSuccessful){
                val projects = response.body()?.listProject
                if (projects.isNullOrEmpty()){
                    emit(Result.Error("No Projects Found"))
                }else{
                    emit(Result.Success(projects))
                }
            }
            else {
                emit(Result.Error("API call failed with code ${response.code()}"))
            }
        }catch (e: Exception) {
            Log.d("DataRepository", "listData: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun addProject(email: String, projectName: String, desc: String, date: String, note: String): LiveData<Result<AddProjectResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.addProject(email, projectName, desc, date, note)
            if (response.projectName.isNullOrEmpty()){
                emit(Result.Error("Project Cannot be Created"))
            }else{
                emit(Result.Success(response))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }
}