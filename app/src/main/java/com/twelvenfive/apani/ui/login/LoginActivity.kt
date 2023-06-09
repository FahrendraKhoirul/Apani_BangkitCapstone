package com.twelvenfive.apani.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.twelvenfive.apani.R
import com.twelvenfive.apani.databinding.ActivityLoginBinding
import com.twelvenfive.apani.ui.home.HomeActivity
import com.twelvenfive.apani.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            login()
        }

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun login() {
        startActivity(Intent(this, HomeActivity::class.java))
    }
}