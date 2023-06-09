package com.twelvenfive.apani.ui.register

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.twelvenfive.apani.R
import com.twelvenfive.apani.databinding.ActivityRegisterBinding
import com.twelvenfive.apani.ui.home.HomeActivity
import com.twelvenfive.apani.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            register()
        }

        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun register() {
        startActivity(Intent(this, HomeActivity::class.java))
    }
}