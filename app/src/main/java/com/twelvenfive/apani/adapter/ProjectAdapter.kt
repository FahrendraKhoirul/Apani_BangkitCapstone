package com.twelvenfive.apani.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.twelvenfive.apani.databinding.ItemProjectBinding
import com.twelvenfive.apani.network.response.ListProjectItem

class ProjectAdapter(private val projectList: List<ListProjectItem>) : RecyclerView.Adapter<ProjectAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: ProjectAdapter.OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: ListProjectItem)
    }

    fun setOnItemClickCallback(onItemClickCallback: ProjectAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ViewHolder(var binding: ItemProjectBinding) : RecyclerView.ViewHolder(binding.root) {
        fun binding(project: ListProjectItem){
            binding.tvTitleProject.text = project.projectName
            binding.tvDescProject.text = project.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectAdapter.ViewHolder {
        val binding = ItemProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectAdapter.ViewHolder, position: Int) {
        val project = projectList[position]
        holder.binding(project)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(project)
        }
    }

    override fun getItemCount(): Int {
        return projectList.size
        Log.d("LIST SIZE", projectList.size.toString())
    }
}