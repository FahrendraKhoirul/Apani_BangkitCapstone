package com.twelvenfive.apani.network.api

import com.twelvenfive.apani.network.response.ArticlesResponse
import com.twelvenfive.apani.network.response.LoginResponse
import com.twelvenfive.apani.network.response.RegisterResponse
import com.twelvenfive.apani.network.response.TokenResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("users/register")
    @FormUrlEncoded
    suspend fun register(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterResponse

    @POST("users/login")
    @FormUrlEncoded
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @GET("users/verifytoken")
    suspend fun verifyToken(
        @Header("Authorization") token: String?
    ): TokenResponse

    @GET("articles")
    suspend fun getAllArticles(
    ) : Response<ArticlesResponse>
}