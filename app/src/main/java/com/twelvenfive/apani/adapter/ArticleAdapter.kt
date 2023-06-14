package com.twelvenfive.apani.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.twelvenfive.apani.databinding.ItemArticleBinding
import com.twelvenfive.apani.network.response.ArticleListItem


class ArticleAdapter(private val articleList: List<ArticleListItem>) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: ArticleListItem)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ViewHolder(var binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun binding(article: ArticleListItem) {
            binding.tvItemAuthor.text = article.author
            binding.tvItemName.text = article.title
            Glide.with(itemView)
                .load(article.imgUrl)
                .into(binding.ivItemPhoto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articleList[position]
        holder.binding(article)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(article)
        }
    }

    override fun getItemCount(): Int {
        return articleList.size
    }
}