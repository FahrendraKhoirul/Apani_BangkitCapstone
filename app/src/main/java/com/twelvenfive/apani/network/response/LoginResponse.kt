package com.twelvenfive.apani.network.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("message")
	val message: String ?= null,

	@field:SerializedName("token")
	val token: String ?= null,
)
