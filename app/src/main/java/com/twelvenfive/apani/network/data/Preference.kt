package com.twelvenfive.apani.network.data

import android.content.Context
import android.content.SharedPreferences
import com.twelvenfive.apani.network.response.LoginResult

class Preference(context: Context) {
    companion object {
        private const val PREFERENCE_NAME = "LoginPreference"
        private const val KEY_TOKEN = "token"
        private const val VALUE_EMAIL = "email"
        private const val VALUE_USERNAME = "username"
        private const val COORDINATE = "coordinate"
        private const val CITY = "city"
    }

    private val preferences: SharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun setData(value: LoginResult) {
        val edit = preferences.edit()
        edit.putString(VALUE_USERNAME, value.username)
        edit.putString(VALUE_EMAIL, value.email)
        edit.putString(KEY_TOKEN, value.token)
        edit.apply()
    }

    fun getData(): LoginResult {
        val username = preferences.getString(VALUE_USERNAME, null)
        val email = preferences.getString(VALUE_EMAIL, null)
        val token = preferences.getString(KEY_TOKEN, null)
        return LoginResult(email, username, token)
    }

    fun clearToken() {
        preferences.edit().remove(KEY_TOKEN).apply()
    }

    fun saveLocation(value: LocationData){
        val edit = preferences.edit()
        edit.putString(COORDINATE, value.coordinate)
        edit.putString(CITY, value.city)
        edit.apply()
    }

    fun getLocation(): LocationData{
        val coordinate = preferences.getString(COORDINATE, null)
        val city = preferences.getString(CITY, null)
        return LocationData(coordinate, city)
    }
}