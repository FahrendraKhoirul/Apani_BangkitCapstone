package com.twelvenfive.apani.ui.project.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.twelvenfive.apani.R
import com.twelvenfive.apani.databinding.ActivityDetailProjectBinding
import com.twelvenfive.apani.network.response.ListProjectItem
import java.text.SimpleDateFormat
import java.util.*

class DetailProjectActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailProjectBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showDetail()
        binding.icBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun showDetail() {
        val data = intent?.getParcelableExtra<ListProjectItem>(EXTRA_DETAIL)
        binding.apply {
            tvTitleProject.text = data?.projectName
            tvDescriptionProjectExplanation.text = data?.description
            val dateString = data?.date
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            try {
                val date = inputFormat.parse(dateString)
                val output = SimpleDateFormat("yyyy-MM-dd", Locale("id", "ID"))
                val formattedDate = output.format(date)
                tvDateProjectDescription.text = formattedDate
            }catch (e: java.lang.Exception){
                e.printStackTrace()
            }
            tvNoteProjectDescription.text = data?.note
        }
    }

    companion object{
        const val EXTRA_DETAIL = "extra_detail"
    }
}