package com.twelvenfive.apani.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ArticlesResponse(

	@field:SerializedName("article_list")
	val articleList: List<ArticleListItem>,

	@field:SerializedName("message")
	val message: String
)

@Parcelize
data class ArticleListItem(

	@field:SerializedName("article_id")
	val articleId: Int,

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("img_url")
	val imgUrl: String,

	@field:SerializedName("author")
	val author: String,

	@field:SerializedName("source")
	val source: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("article")
	val article: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
): Parcelable
