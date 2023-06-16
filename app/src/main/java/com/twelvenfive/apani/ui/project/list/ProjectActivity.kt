package com.twelvenfive.apani.ui.project.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.twelvenfive.apani.R
import com.twelvenfive.apani.adapter.ArticleAdapter
import com.twelvenfive.apani.adapter.ProjectAdapter
import com.twelvenfive.apani.databinding.ActivityProjectBinding
import com.twelvenfive.apani.network.data.Preference
import com.twelvenfive.apani.network.response.ArticleListItem
import com.twelvenfive.apani.network.response.ListProjectItem
import com.twelvenfive.apani.ui.ViewModelFactory
import com.twelvenfive.apani.ui.article.detail.DetailArticleActivity
import com.twelvenfive.apani.ui.article.list.ArticleActivity
import com.twelvenfive.apani.ui.home.HomeActivity
import com.twelvenfive.apani.ui.profile.ProfileActivity
import com.twelvenfive.apani.ui.project.detail.DetailProjectActivity

class ProjectActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProjectBinding
    private lateinit var viewModelFactory: ViewModelFactory
    private val projectViewModel: ProjectViewModel by viewModels { viewModelFactory }
    private var buttonBackPressedOnce = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModelFactory = ViewModelFactory.getInstance(binding.root.context)

        projectViewModel.getAllProject().observe(this){result ->
            when(result){
                is com.twelvenfive.apani.network.data.Result.Loading -> {
                    showLoading(true)
                }
                is com.twelvenfive.apani.network.data.Result.Error -> {
                    showLoading(false)
                    Toast.makeText(this, "Gagal Memuat Project", Toast.LENGTH_SHORT).show()
                }
                is com.twelvenfive.apani.network.data.Result.Success -> {
                    showLoading(false)
                    val projects = result.data
                    showList(projects)
                    Toast.makeText(this, "Berhasil Memuat Project", Toast.LENGTH_SHORT).show()
                }
            }
        }

        bottomNavigation()
    }

    private fun showList(projectList: List<ListProjectItem>) {
        val layoutManager = LinearLayoutManager(this)
        binding.rvProject.layoutManager = layoutManager

        val sortedList = projectList.sortedByDescending { it.createdAt }

        val projectAdapter = ProjectAdapter(sortedList)
        binding.rvProject.adapter = projectAdapter

        projectAdapter.setOnItemClickCallback(object : ProjectAdapter.OnItemClickCallback{
            override fun onItemClicked(data: ListProjectItem) {
                val detail = Intent(this@ProjectActivity, DetailProjectActivity::class.java)
                detail.putExtra(DetailProjectActivity.EXTRA_DETAIL, data)
                startActivity(detail)
            }
        })
    }

    private fun bottomNavigation() {
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

    private fun showLoading(isLoading: Boolean){
        if (isLoading){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }
}