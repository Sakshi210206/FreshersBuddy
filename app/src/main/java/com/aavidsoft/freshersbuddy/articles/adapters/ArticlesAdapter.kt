package com.aavidsoft.freshersbuddy.articles.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aavidsoft.freshersbuddy.R
import com.aavidsoft.freshersbuddy.articles.models.Items

class ArticleAdapter(private var articles: ArrayList<Items>) :
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val articleImage: ImageView = itemView.findViewById(R.id.articleImage)
        val articleTitle: TextView = itemView.findViewById(R.id.articleTitle)
        val articleDescription: TextView = itemView.findViewById(R.id.articleDescription)
        val articleSource: TextView = itemView.findViewById(R.id.articleSource)
        val articleDateTime: TextView = itemView.findViewById(R.id.articleDateTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
         val articleView = layoutInflater.inflate(R.layout.item_articles,null)  
        return ArticleViewHolder(articleView)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.articleTitle.text = article.title
        holder.articleDescription.text = article.body
        holder.articleSource.text = article.author
        holder.articleDateTime.text = article.publishedOn
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    fun updateArticles(newArticles: ArrayList<Items>) {
        articles = newArticles
        notifyDataSetChanged()
    }
}
