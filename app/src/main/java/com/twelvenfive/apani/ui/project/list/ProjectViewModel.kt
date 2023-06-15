package com.twelvenfive.apani.ui.project.list

import androidx.lifecycle.ViewModel
import com.twelvenfive.apani.network.data.DataRepository

class ProjectViewModel(private val dataRepository: DataRepository): ViewModel() {
    fun getAllProject() = dataRepository.getAllProjects()
}