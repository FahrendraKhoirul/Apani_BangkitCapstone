package com.twelvenfive.apani.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.lifecycleScope
import com.twelvenfive.apani.R
import com.twelvenfive.apani.databinding.ActivitySplashBinding
import com.twelvenfive.apani.ui.landing.LandingActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.prefs.Preferences

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME:Long = 3000
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvLogo.animate().alpha(1f).duration = SPLASH_TIME

        lifecycleScope.launch {
            delay(SPLASH_TIME)
            val intent = Intent(this@SplashActivity, LandingActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}