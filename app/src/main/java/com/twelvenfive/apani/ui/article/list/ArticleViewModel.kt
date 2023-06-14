package com.twelvenfive.apani.ui.article.list

import androidx.lifecycle.ViewModel
import com.twelvenfive.apani.network.data.DataRepository

class ArticleViewModel(private val dataRepository: DataRepository): ViewModel(){
    fun getAllArticles() = dataRepository.getAllArticles()
}