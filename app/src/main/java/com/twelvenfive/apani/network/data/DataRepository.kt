package com.twelvenfive.apani.network.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.twelvenfive.apani.network.api.ApiService
import com.twelvenfive.apani.network.response.*

class DataRepository(private val apiService: ApiService, private val preference: Preference) {
    companion object{
        @Volatile
        private var instance: DataRepository? = null
        fun getInstance(
            apiService: ApiService,
            preference: Preference
        ): DataRepository =
            instance ?: synchronized(this){
                instance ?: DataRepository(apiService, preference)
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
            if (response.token?.isNotEmpty() == true){
                emit(Result.Success(response))
            }else{
                emit(Result.Error(response.message!!))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    /*fun setToken(): LiveData<Result<TokenResponse>> = liveData{
        emit(Result.Loading)
        val token = preference.getToken()
        try {
            val response = apiService.verifyToken(token)
            if (response.data.email.isNotEmpty()){
                emit(Result.Success(response))
            }else{
                emit(Result.Error(response.message))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }*/


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
            Log.d("StoryRepository", "listStory: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }
}