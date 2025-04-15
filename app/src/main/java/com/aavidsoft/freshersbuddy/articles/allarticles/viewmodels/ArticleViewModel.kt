package com.aavidsoft.freshersbuddy.articles.allarticles.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aavidsoft.freshersbuddy.articles.allarticles.models.Items
import com.aavidsoft.freshersbuddy.articles.allarticles.models.ItemsDetail
import com.aavidsoft.freshersbuddy.articles.allarticles.repositories.ArticleRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ArticleViewModel(private val articleRepository: ArticleRepository) : ViewModel() {
    val articleUpdateAvailableLiveData = MutableLiveData<Boolean>()
    val articleDetailUpdateAvailableLiveData = MutableLiveData<Boolean>()
    val items = ArrayList<Items>()
    val itemsDetail = ArrayList<ItemsDetail>()

    fun fetchArticles(page: Int, size: Int) {

        CoroutineScope(Dispatchers.IO).launch {
            val items = articleRepository.fetchItems(page, size)
            withContext(Dispatchers.Main) {
                if (items != null) {
                    this@ArticleViewModel.items.addAll(items)
                }
                articleUpdateAvailableLiveData.postValue(true)
            }
        }
    }

    fun fetchArticleDetails(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val itemsDetail = articleRepository.fetchItemDetails(id)
            withContext(Dispatchers.Main) {
                if (itemsDetail != null) {
                    this@ArticleViewModel.itemsDetail.addAll(itemsDetail)

                    articleDetailUpdateAvailableLiveData.postValue(true)
                }
            }
        }
    }
}