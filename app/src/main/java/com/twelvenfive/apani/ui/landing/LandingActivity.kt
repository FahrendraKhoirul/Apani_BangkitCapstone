package com.twelvenfive.apani.ui.landing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.twelvenfive.apani.R
import com.twelvenfive.apani.databinding.ActivityLandingBinding
import com.twelvenfive.apani.ui.login.LoginActivity
import com.twelvenfive.apani.ui.register.RegisterActivity

class LandingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLandingBinding
    private var buttonBackPressedOnce = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLoginLanding.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.btnRegisterLanding.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
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
}