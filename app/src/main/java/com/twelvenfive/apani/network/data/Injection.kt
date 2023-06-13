package com.twelvenfive.apani.network.data

import android.content.Context
import android.preference.Preference
import com.twelvenfive.apani.network.api.ApiConfig

object Injection {
    fun provideRepository(context: Context): DataRepository {
        val apiService = ApiConfig.getApiService()
        val preference = com.twelvenfive.apani.network.data.Preference(context)
        return DataRepository.getInstance(apiService, preference)
    }
}