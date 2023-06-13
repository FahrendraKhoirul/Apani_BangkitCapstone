package com.twelvenfive.apani.ui.project.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.twelvenfive.apani.R
import com.twelvenfive.apani.databinding.ActivityAddProjectBinding

class AddProjectActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddProjectBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.icBack.setOnClickListener {
            onBackPressed()
        }
    }
}