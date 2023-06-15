package com.twelvenfive.apani.network.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("loginResult")
	val loginResult: LoginResult? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class LoginResult(

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("token")
	val token: String? = null
)
