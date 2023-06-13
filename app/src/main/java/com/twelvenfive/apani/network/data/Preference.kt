package com.twelvenfive.apani.network.data

import android.content.Context
import android.content.SharedPreferences
import com.twelvenfive.apani.network.response.Data
import com.twelvenfive.apani.network.response.LoginResponse
import com.twelvenfive.apani.network.response.TokenResponse

class Preference(context: Context) {
    companion object {
        private const val PREFERENCE_NAME = "LoginPreference"
        private const val MESSAGE = "message"
        private const val KEY_TOKEN = "token"
        private const val EMAIL = "email"
        private const val PASS = "password"
    }

    private val preferences: SharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun saveToken(value: LoginResponse) {
        val edit = preferences.edit()
        edit.putString(MESSAGE, value.message)
        edit.putString(KEY_TOKEN, value.token)
        edit.apply()
    }

    fun getToken(): LoginResponse {
        val msg = preferences.getString(MESSAGE, null)
        val token = preferences.getString(KEY_TOKEN, null)
        return LoginResponse(msg, token)
    }

    fun clearToken() {
        preferences.edit().remove(KEY_TOKEN).apply()
    }
}