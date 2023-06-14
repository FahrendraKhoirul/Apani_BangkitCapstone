package com.twelvenfive.apani.ui.register

import androidx.lifecycle.ViewModel
import com.twelvenfive.apani.network.data.DataRepository

class RegisterViewModel(private val dataRepository: DataRepository): ViewModel() {
    fun register(username: String, email: String, pass: String) = dataRepository.register(username, email, pass)
}