package com.twelvenfive.apani.ui.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.twelvenfive.apani.R
import com.twelvenfive.apani.databinding.ActivityHomeBinding
import com.twelvenfive.apani.network.data.Preference
import com.twelvenfive.apani.ui.article.list.ArticleActivity
import com.twelvenfive.apani.ui.crops.CropsActivity
import com.twelvenfive.apani.ui.disease.DiseaseActivity
import com.twelvenfive.apani.ui.fertilizer.FertilizerActivity
import com.twelvenfive.apani.ui.landing.LandingActivity
import com.twelvenfive.apani.ui.login.LoginActivity
import com.twelvenfive.apani.ui.profile.ProfileActivity
import com.twelvenfive.apani.ui.project.add.AddProjectActivity
import com.twelvenfive.apani.ui.project.list.ProjectActivity
import java.text.SimpleDateFormat
import java.util.*

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var buttonBackPressedOnce = false
    private val calendar = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preference = Preference(this)
        val token = preference.getToken().token
        if (token.isNullOrEmpty()){
            val intent = Intent(this, LandingActivity::class.java)
            startActivity(intent)
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if (hasLocationPermission()) {
            getMyLastLocation()
        } else {
            requestLocationPermission()
        }

        showtime()
        showtoday()
        bottomNavigation()
        cardViewClicked()
    }

    private fun showtoday() {
        val dateFormat = SimpleDateFormat("EEEE, d MMMM yyyy", Locale("id", "ID"))
        val formattedDate = dateFormat.format(calendar.time)
        binding.tvDate.text = formattedDate
    }

    private fun showtime() {
        val hour = calendar.get(Calendar.HOUR_OF_DAY)

        val greeting: String = when(hour) {
            in 0..11 -> getString(R.string.good_morning)
            in 12..14 -> getString(R.string.good_afternoon)
            in 15..18 -> getString(R.string.good_afternoon_sore)
            else -> getString(R.string.good_evening)
        }

        val greetingMsg = getString(R.string.hello_user, greeting)
        binding.tvHaloUser.text = greetingMsg
    }

    private fun hasLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_CODE_PERMISSIONS
        )
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getMyLastLocation()
            }else{
                Toast.makeText(this, getString(R.string.permission_is_not_allowed), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun bottomNavigation() {
        binding.bottomNavigation.selectedItemId = R.id.action_home

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId){
                R.id.action_home -> {
                    true
                }
                R.id.action_project -> {
                    startActivity(Intent(this, ProjectActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.action_article -> {
                    startActivity(Intent(this, ArticleActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.action_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
            }
            false
        }
    }

    private fun cardViewClicked() {
        binding.cvProject.setOnClickListener {
            startActivity(Intent(this, AddProjectActivity::class.java))
            overridePendingTransition(0, 0)
        }

        binding.cvRecommended.setOnClickListener {
            startActivity(Intent(this, CropsActivity::class.java))
            overridePendingTransition(0, 0)
        }

        binding.cvFertilizer.setOnClickListener {
            startActivity(Intent(this, FertilizerActivity::class.java))
            overridePendingTransition(0, 0)
        }

        binding.cvDisease.setOnClickListener {
            startActivity(Intent(this, DiseaseActivity::class.java))
            overridePendingTransition(0, 0)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (buttonBackPressedOnce) {
            super.onBackPressed()
        } else {
            buttonBackPressedOnce = true
            Toast.makeText(this, getString(R.string.press_back_again_to_exit), Toast.LENGTH_SHORT).show()
            Handler(Looper.getMainLooper()).postDelayed({
                buttonBackPressedOnce = false
            }, 2000) // Delay to reset the flag after 2 seconds
        }
    }

    companion object{
        private const val REQUEST_CODE_PERMISSIONS = 10
    }
}