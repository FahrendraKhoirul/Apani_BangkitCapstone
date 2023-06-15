package com.twelvenfive.apani.network.api

import com.twelvenfive.apani.network.response.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST("users/register")
    @FormUrlEncoded
    suspend fun register(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterResponse

    @GET("articles")
    suspend fun getAllArticles(
    ) : Response<ArticlesResponse>

    @POST("users/login")
    @FormUrlEncoded
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse
}