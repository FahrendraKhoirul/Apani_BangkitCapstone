package com.twelvenfive.apani.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.twelvenfive.apani.R
import com.twelvenfive.apani.databinding.ActivityHomeBinding
import com.twelvenfive.apani.ui.article.list.ArticleActivity
import com.twelvenfive.apani.ui.crops.CropsActivity
import com.twelvenfive.apani.ui.disease.DiseaseActivity
import com.twelvenfive.apani.ui.fertilizer.FertilizerActivity
import com.twelvenfive.apani.ui.profile.ProfileActivity
import com.twelvenfive.apani.ui.project.add.AddProjectActivity
import com.twelvenfive.apani.ui.project.list.ProjectActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavigation()
        cardViewClicked()

    }
    private fun bottomNavigation() {
        binding.bottomNavigation.selectedItemId = R.id.action_home

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId){
                R.id.action_home -> {
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
                    startActivity(Intent(this, ProfileActivity::class.java))
                    overridePendingTransition(0, 0)
                    true
                }
            }
            false
        }
    }

    private fun cardViewClicked() {
        binding.cvProject.setOnClickListener {
            startActivity(Intent(this, AddProjectActivity::class.java))
            overridePendingTransition(0, 0)
        }

        binding.cvRecommended.setOnClickListener {
            startActivity(Intent(this, CropsActivity::class.java))
            overridePendingTransition(0, 0)
        }

        binding.cvFertilizer.setOnClickListener {
            startActivity(Intent(this, FertilizerActivity::class.java))
            overridePendingTransition(0, 0)
        }

        binding.cvDisease.setOnClickListener {
            startActivity(Intent(this, DiseaseActivity::class.java))
            overridePendingTransition(0, 0)
        }
    }
}