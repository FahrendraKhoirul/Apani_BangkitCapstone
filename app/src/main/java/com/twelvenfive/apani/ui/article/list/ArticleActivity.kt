package com.twelvenfive.apani.ui.article.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.twelvenfive.apani.R
import com.twelvenfive.apani.adapter.ArticleAdapter
import com.twelvenfive.apani.databinding.ActivityArticleBinding
import com.twelvenfive.apani.network.response.ArticleListItem
import com.twelvenfive.apani.ui.ViewModelFactory
import com.twelvenfive.apani.ui.article.detail.DetailArticleActivity
import com.twelvenfive.apani.ui.home.HomeActivity
import com.twelvenfive.apani.ui.profile.ProfileActivity
import com.twelvenfive.apani.ui.project.list.ProjectActivity

class ArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticleBinding
    private lateinit var viewModelFactory: ViewModelFactory
    private val articleViewModel: ArticleViewModel by viewModels{ viewModelFactory}
    private var buttonBackPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModelFactory = ViewModelFactory.getInstance(binding.root.context)

        articleViewModel.getAllArticles().observe(this) { result ->
            when (result) {
                is com.twelvenfive.apani.network.data.Result.Loading -> {
                    showLoading(true)
                }
                is com.twelvenfive.apani.network.data.Result.Error -> {
                    showLoading(false)
                    Toast.makeText(this, "Gagal Memuat Artikel", Toast.LENGTH_SHORT).show()
                }
                is com.twelvenfive.apani.network.data.Result.Success -> {
                    showLoading(false)
                    val articles = result.data
                    showList(articles)
                    Toast.makeText(this, "Berhasil Memuat Artikel", Toast.LENGTH_SHORT).show()
                }
            }
        }

        bottomNavigation()
    }

    private fun showList(articleList: List<ArticleListItem>) {
        val layoutManager = LinearLayoutManager(this)
        binding.rvArticle.layoutManager = layoutManager

        val articleAdapter = ArticleAdapter(articleList)
        binding.rvArticle.adapter = articleAdapter

        articleAdapter.setOnItemClickCallback(object : ArticleAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ArticleListItem) {
                val detail = Intent(this@ArticleActivity, DetailArticleActivity::class.java)
                detail.putExtra(DetailArticleActivity.EXTRA_DETAIL, data)
                startActivity(detail)
            }
        })
    }

    private fun bottomNavigation() {
        binding.bottomNavigation.selectedItemId = R.id.action_article

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

    private fun showLoading(isLoading: Boolean){
        if (isLoading){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
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