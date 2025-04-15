package com.aavidsoft.freshersbuddy.articles.allarticles.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aavidsoft.freshersbuddy.R
import com.aavidsoft.freshersbuddy.articles.allarticles.models.Items
import com.aavidsoft.freshersbuddy.databinding.ItemArticlesBinding

class ArticleAdapter(private var items: ArrayList<Items>) :
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(items: Items,position: Int,articleAdapter: ArticleAdapter)
    }

    var onItemClickListener: OnItemClickListener ?= null

    inner class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val articleViewHolderBinding = ItemArticlesBinding.bind(view)

        init {
            articleViewHolderBinding.root.setOnClickListener {
                onItemClickListener?.onItemClick(items[adapterPosition],adapterPosition,this@ArticleAdapter)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_articles, parent,false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.articleViewHolderBinding.itemObject = items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
