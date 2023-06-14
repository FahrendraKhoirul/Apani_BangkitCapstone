package com.twelvenfive.apani.ui.project.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.twelvenfive.apani.R
import com.twelvenfive.apani.databinding.ActivityProjectBinding
import com.twelvenfive.apani.ui.article.list.ArticleActivity
import com.twelvenfive.apani.ui.home.HomeActivity
import com.twelvenfive.apani.ui.profile.ProfileActivity

class ProjectActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProjectBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.selectedItemId = R.id.action_project

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.action_project -> {
                    true
                }
                R.id.action_article -> {
                    startActivity(Intent(this, ArticleActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
                R.id.action_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
            }
            false
        }
    }
}