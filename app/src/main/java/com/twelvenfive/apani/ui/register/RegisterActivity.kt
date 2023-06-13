package com.twelvenfive.apani.ui.register

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.twelvenfive.apani.R
import com.twelvenfive.apani.databinding.ActivityRegisterBinding
import com.twelvenfive.apani.ui.ViewModelFactory
import com.twelvenfive.apani.ui.home.HomeActivity
import com.twelvenfive.apani.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    private lateinit var viewModelFactory: ViewModelFactory
    private val registerViewModel: RegisterViewModel by viewModels { viewModelFactory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModelFactory = ViewModelFactory.getInstance(binding.root.context)

        binding.btnRegister.setOnClickListener {
            register()
        }

        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun register() {
        val username = binding.etUsernameRegister.text?.trim().toString()
        val email = binding.etEmailRegister.text?.trim().toString()
        val pass = binding.etPasswordRegister.text?.trim().toString()

        if (binding.etUsernameRegister.text.isNullOrEmpty() ||binding.etEmailRegister.text.isNullOrEmpty() || binding.etPasswordRegister.text.isNullOrEmpty()){
            Toast.makeText(this, getString(R.string.input_first), Toast.LENGTH_SHORT).show()
        }
        if (binding.etUsernameRegister.error != null ||binding.etEmailRegister.error != null || binding.etPasswordRegister.error != null){
            Toast.makeText(this, getString(R.string.input_correctly), Toast.LENGTH_SHORT).show()
        }else{
            registerViewModel.register(username, email, pass).observe(this){register ->
                if (register != null){
                    when(register){
                        is com.twelvenfive.apani.network.data.Result.Loading -> {
                            showLoading(true)
                        }
                        is com.twelvenfive.apani.network.data.Result.Error -> {
                            showLoading(false)
                            Toast.makeText(this, getString(R.string.register_failed), Toast.LENGTH_SHORT).show()
                        }
                        is com.twelvenfive.apani.network.data.Result.Success -> {
                            showLoading(false)
                            startActivity(Intent(this, LoginActivity::class.java))
                            Toast.makeText(this, getString(R.string.register_success), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean){
        if (isLoading){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }
}