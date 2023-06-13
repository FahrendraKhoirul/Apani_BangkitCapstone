package com.twelvenfive.apani.network.response

import com.google.gson.annotations.SerializedName

data class TokenResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String
)

data class Data(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("email")
	val email: String
)
