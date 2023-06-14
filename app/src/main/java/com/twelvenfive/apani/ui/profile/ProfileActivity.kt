package com.twelvenfive.apani.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
    private var buttonBackPressedOnce = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModelFactory = ViewModelFactory.getInstance(binding.root.context)

        binding.icLogout.setOnClickListener {
            logout()
        }

        bottomNavigation()
    }

    private fun logout() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle(getString(R.string.logout))
        dialog.setMessage(getString(R.string.sure))
        dialog.setPositiveButton(getString(R.string.yes)) { _, _ ->
            clearData()
            Toast.makeText(this, "Keluar Berhasil", Toast.LENGTH_SHORT).show()
        }
        dialog.setNegativeButton(getString(R.string.no)) { _, _ ->
            Toast.makeText(this, "Keluar Gagal", Toast.LENGTH_SHORT).show()
        }
        dialog.show()
    }

    private fun clearData() {
        val preference = Preference(this)
        preference.clearToken()
        startActivity(Intent(this, LoginActivity::class.java))
    }

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