package com.twelvenfive.apani.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.twelvenfive.apani.R
import com.twelvenfive.apani.databinding.ActivitySplashBinding
import com.twelvenfive.apani.network.data.Preference
import com.twelvenfive.apani.network.response.LoginResult
import com.twelvenfive.apani.ui.home.HomeActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME:Long = 3000
    private lateinit var binding: ActivitySplashBinding
    private lateinit var preferences: Preference
    private lateinit var loginResult: LoginResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivLogo.animate().alpha(1f).duration = SPLASH_TIME

        preferences = Preference(this)
        loginResult = preferences.getData()
        lifecycleScope.launch {
            delay(SPLASH_TIME)
            val intent = Intent(this@SplashActivity, HomeActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.transition.fade_in, R.transition.fade_out)
            finish()
        }
    }
}