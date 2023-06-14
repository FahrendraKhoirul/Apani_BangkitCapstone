package com.twelvenfive.apani.ui.crops

import android.annotation.SuppressLint
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.twelvenfive.apani.R
import com.twelvenfive.apani.databinding.ActivityCropsBinding
import java.util.*

class CropsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCropsBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCropsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        binding.icBack.setOnClickListener {
            onBackPressed()
        }
        getMyLastLocation()
    }

    @SuppressLint("MissingPermission")
    private fun getMyLastLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location : Location? ->
            if (location != null) {
                val geocoder = Geocoder(this, Locale.getDefault())
                val addresses = geocoder.getFromLocation(
                    location.latitude,
                    location.longitude,
                    1
                )
                val cityName = addresses?.get(0)?.locality

                binding.tvLocation.text = cityName
            }
        }
    }
}