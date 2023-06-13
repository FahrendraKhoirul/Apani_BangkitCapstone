package com.twelvenfive.apani.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.twelvenfive.apani.network.data.DataRepository
import com.twelvenfive.apani.network.data.Injection
import com.twelvenfive.apani.ui.article.list.ArticleViewModel
import com.twelvenfive.apani.ui.home.HomeViewModel
import com.twelvenfive.apani.ui.login.LoginViewModel
import com.twelvenfive.apani.ui.profile.ProfileViewModel
import com.twelvenfive.apani.ui.register.RegisterViewModel

class ViewModelFactory private constructor(private val dataRepository: DataRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ArticleViewModel::class.java)){
            return ArticleViewModel(dataRepository) as T
        }

        if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(dataRepository) as T
        }

        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)){
            return RegisterViewModel(dataRepository) as T
        }

        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(dataRepository) as T
        }

        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)){
            return ProfileViewModel(dataRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}