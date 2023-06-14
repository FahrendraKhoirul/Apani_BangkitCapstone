package com.twelvenfive.apani.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.twelvenfive.apani.R
import com.twelvenfive.apani.databinding.ActivityProfileBinding
import com.twelvenfive.apani.network.data.Preference
import com.twelvenfive.apani.network.response.Data
import com.twelvenfive.apani.network.response.TokenResponse
import com.twelvenfive.apani.ui.ViewModelFactory
import com.twelvenfive.apani.ui.article.list.ArticleActivity
import com.twelvenfive.apani.ui.home.HomeActivity
import com.twelvenfive.apani.ui.login.LoginActivity
import com.twelvenfive.apani.ui.login.LoginViewModel
import com.twelvenfive.apani.ui.project.list.ProjectActivity

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var viewModelFactory: ViewModelFactory
    private val profileViewModel: ProfileViewModel by viewModels { viewModelFactory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModelFactory = ViewModelFactory.getInstance(binding.root.context)

        binding.icLogout.setOnClickListener {
            logout()
        }

        bottomNavigation()

        /*profileViewModel.setToken().observe(this){token ->
            if (token != null){
                when(token){
                    is com.twelvenfive.apani.network.data.Result.Loading -> {

                    }
                    is com.twelvenfive.apani.network.data.Result.Error -> {
                        Toast.makeText(this, "Authentikasi Gagal", Toast.LENGTH_SHORT).show()
                    }
                    is com.twelvenfive.apani.network.data.Result.Success -> {
                        saveData(token.data)
                        Toast.makeText(this, "Authentikasi Berhasil", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }*/
    }

    private fun logout() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle(getString(R.string.logout))
        dialog.setMessage(getString(R.string.sure))
        dialog.setPositiveButton(getString(R.string.yes)) { _, _ ->
            clearData()
            Toast.makeText(this, "Keluar Berhasil", Toast.LENGTH_SHORT).show()
        }
        dialog.setNegativeButton("No") { _, _ ->
            Toast.makeText(this, "Keluar Gagal", Toast.LENGTH_SHORT).show()
        }
        dialog.show()
    }

    private fun clearData() {
        val preference = Preference(this)
        preference.clearToken()
        startActivity(Intent(this, LoginActivity::class.java))
    }

    /*private fun saveData(data: TokenResponse) {
        val preference = Preference(this)
        val tokenRes = data.data
        val tokenResponse = Data(password = tokenRes.password, email = tokenRes.email)
        preference.saveData(tokenResponse)
    }*/

    private fun bottomNavigation() {
        binding.bottomNavigation.selectedItemId = R.id.action_profile

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    overridePendingTransition(0, 0)
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
                    true
                }
            }
            false
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}