package com.twelvenfive.apani.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.twelvenfive.apani.R
import com.twelvenfive.apani.databinding.ActivityLoginBinding
import com.twelvenfive.apani.network.data.Preference
import com.twelvenfive.apani.network.data.Result
import com.twelvenfive.apani.network.response.LoginResponse
import com.twelvenfive.apani.network.response.LoginResult
import com.twelvenfive.apani.ui.ViewModelFactory
import com.twelvenfive.apani.ui.home.HomeActivity
import com.twelvenfive.apani.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModelFactory: ViewModelFactory
    private val loginViewModel: LoginViewModel by viewModels { viewModelFactory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModelFactory = ViewModelFactory.getInstance(binding.root.context)

        binding.btnLogin.setOnClickListener {
            login()
        }

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun login() {
        val email = binding.etEmailLogin.text?.trim().toString()
        val pass = binding.etPasswordLogin.text?.trim().toString()

        if (binding.etEmailLogin.text.isNullOrEmpty() || binding.etPasswordLogin.text.isNullOrEmpty()){
            Toast.makeText(this, getString(R.string.input_first), Toast.LENGTH_SHORT).show()
        }

        if (binding.etEmailLogin.error != null || binding.etPasswordLogin.error != null){
            Toast.makeText(this, getString(R.string.input_correctly), Toast.LENGTH_SHORT).show()
        }else{
            loginViewModel.login(email, pass).observe(this){login ->
                if (login != null){
                    when(login){
                        is Result.Loading -> {
                            showLoading(true)
                        }
                        is Result.Error -> {
                            showLoading(false)
                            Toast.makeText(this, "Masuk Gagal", Toast.LENGTH_SHORT).show()
                        }
                        is Result.Success -> {
                            showLoading(false)
                            saveData(login.data)
                            Toast.makeText(this, "Masuk Berhasil", Toast.LENGTH_SHORT).show()
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    private fun saveData(data: LoginResponse) {
        val preference = Preference(this)
        val loginRes = data.loginResult
        val loginResult = LoginResult(email = loginRes?.email, username = loginRes?.username, token = loginRes?.token)
        preference.setData(loginResult)
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun showLoading(isLoading: Boolean){
        if (isLoading){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}