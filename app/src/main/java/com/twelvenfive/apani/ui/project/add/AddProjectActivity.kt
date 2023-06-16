package com.twelvenfive.apani.ui.project.add

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.viewModels
import com.twelvenfive.apani.R
import com.twelvenfive.apani.databinding.ActivityAddProjectBinding
import com.twelvenfive.apani.network.data.Preference
import com.twelvenfive.apani.ui.ViewModelFactory
import com.twelvenfive.apani.ui.login.LoginViewModel
import com.twelvenfive.apani.ui.project.list.ProjectActivity
import java.text.SimpleDateFormat
import java.util.*

class AddProjectActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddProjectBinding
    private lateinit var viewModelFactory: ViewModelFactory
    private val addProjectViewModel: AddProjectViewModel by viewModels { viewModelFactory }
    private val calendar: Calendar = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModelFactory = ViewModelFactory.getInstance(binding.root.context)

        binding.icBack.setOnClickListener {
            onBackPressed()
        }

        binding.btnAddProject.setOnClickListener {
            addProject()
        }
    }

    private fun addProject() {
        val pref = Preference(this)
        val email = pref.getData().email
        val projectName = binding.etTitleProject.text?.trim().toString()
        val desc = binding.etDescProject.text?.trim().toString()
        val date = binding.etDateProject.text.toString()
        val note = binding.etNoteProject.text?.trim().toString()

        if (projectName.isEmpty() || desc.isEmpty() || date.isEmpty() || note.isEmpty()){
            Toast.makeText(this, getString(R.string.input_first), Toast.LENGTH_SHORT).show()
        }else{
            addProjectViewModel.addProject(email.toString(), projectName, desc, date, note).observe(this){data ->
                if (data != null){
                    when(data){
                        is com.twelvenfive.apani.network.data.Result.Loading -> {
                            showLoading(true)
                        }
                        is com.twelvenfive.apani.network.data.Result.Error -> {
                            showLoading(false)
                            Toast.makeText(this, "Tambah Proyek Gagal", Toast.LENGTH_SHORT).show()
                        }
                        is com.twelvenfive.apani.network.data.Result.Success -> {
                            showLoading(false)
                            Toast.makeText(this, "Tambah Proyek Berhasil", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, ProjectActivity::class.java)
                            startActivity(intent)
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    fun showDatePicker(view: View){
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
            calendar.set(selectedYear, selectedMonth, selectedDay)

            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale("id", "ID"))
            val dateString = dateFormat.format(calendar.time)
            binding.etDateProject.setText(dateString)
        }, year, month, day)

        datePickerDialog.show()
    }

    private fun showLoading(isLoading: Boolean){
        if (isLoading){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }
}