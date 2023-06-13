package com.twelvenfive.apani.ui.login

import androidx.lifecycle.ViewModel
import com.twelvenfive.apani.network.data.DataRepository

class LoginViewModel(private val dataRepository: DataRepository): ViewModel() {
    fun login(email: String, pass: String) = dataRepository.login(email, pass)
}