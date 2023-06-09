package com.twelvenfive.apani.ui.landing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.twelvenfive.apani.R
import com.twelvenfive.apani.databinding.ActivityLandingBinding
import com.twelvenfive.apani.ui.login.LoginActivity
import com.twelvenfive.apani.ui.register.RegisterActivity

class LandingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLandingBinding
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
}