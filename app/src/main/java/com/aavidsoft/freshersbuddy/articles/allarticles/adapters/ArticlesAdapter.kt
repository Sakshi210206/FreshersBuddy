package com.aavidsoft.freshersbuddy.articles.allarticles.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aavidsoft.freshersbuddy.R
import com.aavidsoft.freshersbuddy.articles.allarticles.models.Items
import com.aavidsoft.freshersbuddy.databinding.ItemArticlesBinding

class ArticlesAdapter(private var items: ArrayList<Items>) :
    RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>() {

    interface OnItemsClickListener {
        fun onItemsClick(
            items: Items,
            position: Int,
            articlesAdapter: ArticlesAdapter
        )
    }

    var onItemsClickListener: OnItemsClickListener? = null

    inner class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val articleViewHolderBinding = ItemArticlesBinding.bind(view)

        init {
            articleViewHolderBinding.root.setOnClickListener {
                onItemsClickListener?.onItemsClick(
                    items[adapterPosition],
                    adapterPosition,
                    this@ArticlesAdapter
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_articles, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.articleViewHolderBinding.itemObject = items[position]
    }

    override fun getItemCount(): Int = items.size
}
