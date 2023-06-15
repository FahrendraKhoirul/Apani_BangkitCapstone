package com.twelvenfive.apani.ui.article.detail

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Transition
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.twelvenfive.apani.R
import com.twelvenfive.apani.databinding.ActivityDetailArticleBinding
import com.twelvenfive.apani.databinding.DialogImageViewBinding
import com.twelvenfive.apani.network.response.ArticleListItem
import java.text.SimpleDateFormat
import java.util.*

class DetailArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailArticleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showDetail()
        binding.icBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun showImageDialog(imgUrl: String?) {
        val dialog = Dialog(this)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setContentView(R.layout.dialog_image_view)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)

        val imageView = dialog.findViewById<ImageView>(R.id.dialogImageView)

        Glide.with(this)
            .load(imgUrl)
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: com.bumptech.glide.request.transition.Transition<in Drawable>?
                ) {
                    imageView.setImageDrawable(resource)
                    dialog.show()
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    // Do nothing
                }
            })

        dialog.show()
    }

    private fun showDetail() {
        val data = intent?.getParcelableExtra<ArticleListItem>(EXTRA_DETAIL)
        binding.apply {
            tvTitleArticle.text = data?.title
            tvAuthorArticle.text = data?.author
            val dateString = data?.date
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            try {
                val date = inputFormat.parse(dateString)
                val output = SimpleDateFormat("yyyy-MM-dd", Locale("id", "ID"))
                val formattedDate = output.format(date)
                tvDateArticle.text = formattedDate
            }catch (e: java.lang.Exception){
                e.printStackTrace()
            }
            tvDetailArticle.text = data?.article
            if (data?.imgUrl.isNullOrEmpty()){
                binding.ivCoverArticle.setImageDrawable(ContextCompat.getDrawable(this@DetailArticleActivity, R.drawable.image_cover))
            }else{
                Glide.with(this@DetailArticleActivity)
                    .load(data?.imgUrl)
                    .into(ivCoverArticle)
            }

            ivCoverArticle.setOnClickListener {
                showImageDialog(data?.imgUrl)
            }
        }
    }

    companion object{
        const val EXTRA_DETAIL = "extra_detail"
    }
}