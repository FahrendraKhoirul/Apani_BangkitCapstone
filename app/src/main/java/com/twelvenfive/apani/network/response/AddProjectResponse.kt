package com.twelvenfive.apani.network.response

import com.google.gson.annotations.SerializedName

data class AddProjectResponse(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("note")
	val note: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("project_name")
	val projectName: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("status")
	val status: Boolean,
)
