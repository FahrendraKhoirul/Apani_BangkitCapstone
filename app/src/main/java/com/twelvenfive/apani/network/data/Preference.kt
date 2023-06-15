package com.twelvenfive.apani.network.data

import android.content.Context
import android.content.SharedPreferences
import com.twelvenfive.apani.network.response.Data
import com.twelvenfive.apani.network.response.LoginResponse
import com.twelvenfive.apani.network.response.LoginResult
import com.twelvenfive.apani.network.response.TokenResponse

class Preference(context: Context) {
    companion object {
        private const val PREFERENCE_NAME = "LoginPreference"
        private const val KEY_TOKEN = "token"
        private const val EMAIL = "email"
        private const val USERNAME = "username"
    }

    private val preferences: SharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun setData(value: LoginResult) {
        val edit = preferences.edit()
        edit.putString(KEY_TOKEN, value.username)
        edit.putString(KEY_TOKEN, value.email)
        edit.putString(KEY_TOKEN, value.token)
        edit.apply()
    }

    fun getData(): LoginResult {
        val username = preferences.getString(USERNAME, null)
        val email = preferences.getString(EMAIL, null)
        val token = preferences.getString(KEY_TOKEN, null)
        return LoginResult(username, email, token)
    }

    fun clearToken() {
        preferences.edit().remove(KEY_TOKEN).apply()
    }
}