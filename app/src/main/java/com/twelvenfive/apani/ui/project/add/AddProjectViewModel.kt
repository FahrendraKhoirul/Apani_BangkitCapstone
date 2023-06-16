package com.twelvenfive.apani.ui.project.add

import androidx.lifecycle.ViewModel
import com.twelvenfive.apani.network.data.DataRepository

class AddProjectViewModel(private val dataRepository: DataRepository): ViewModel() {
    fun addProject(email: String, projectName: String, desc: String, date: String, note: String) =
        dataRepository.addProject(email, projectName, desc, date, note)
}