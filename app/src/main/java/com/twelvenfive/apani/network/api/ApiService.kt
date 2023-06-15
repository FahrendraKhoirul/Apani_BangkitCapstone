package com.twelvenfive.apani.network.api

import com.twelvenfive.apani.network.response.*
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

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

    @GET("articles")
    suspend fun getAllArticles(
    ) : Response<ArticlesResponse>

    @GET("projects/{email}")
    suspend fun getAllProjects(
        @Path("email") email: String
    ): Response<ProjectsResponse>

    @POST("projects/create")
    suspend fun addProject(
        @Field("email") email: String,
        @Field("project_name") project_name: String,
        @Field("description") description: String,
        @Field("date") date: String,
        @Field("note") note: String,
        @Field("status") status: Boolean
    ): AddProjectResponse
}