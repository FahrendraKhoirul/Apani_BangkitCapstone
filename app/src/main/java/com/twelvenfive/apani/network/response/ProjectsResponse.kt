package com.twelvenfive.apani.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ProjectsResponse(

	@field:SerializedName("list_project")
	val listProject: List<ListProjectItem>,

	@field:SerializedName("message")
	val message: String
)

@Parcelize
data class ListProjectItem(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("note")
	val note: String,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("project_id")
	val projectId: Int,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("project_name")
	val projectName: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("status")
	val status: Boolean,

	@field:SerializedName("updatedAt")
	val updatedAt: String
): Parcelable
