package com.aavidsoft.freshersbuddy.articles.allarticles.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aavidsoft.freshersbuddy.articles.allarticles.models.Items
import com.aavidsoft.freshersbuddy.articles.allarticles.repositories.ArticleRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArticleViewModel(
    private val articleRepository: ArticleRepository
) : ViewModel() {

    val articleUpdateAvailableLiveData = MutableLiveData<Boolean>()
    val items = ArrayList<Items>()

    var pageNo: Int = 0
    var pageSize: Int = 5
    var hasMoreData = true
    var isFetchingArticle = false
    fun fetchArticles() {

        if (!hasMoreData) {
            articleUpdateAvailableLiveData.postValue(false)
            return;
        }
        if (isFetchingArticle) {
            return
        }
        isFetchingArticle = true

        CoroutineScope(Dispatchers.IO).launch {
            try {

                Log.e("vishal", "page: ${pageNo + 1}")

                val items = articleRepository.fetchItems(
                    ++pageNo,
                    pageSize
                )
                if (items != null) {
                    withContext(Dispatchers.Main) {
                        this@ArticleViewModel.items.addAll(items)
                        articleUpdateAvailableLiveData.postValue(true)
                        if (items.size < pageSize) {
                            hasMoreData = false
                        }
                    }
                } else {
                    articleUpdateAvailableLiveData.postValue(false)
                }
            } catch (e: Exception) {
                articleUpdateAvailableLiveData.postValue(false)

            } finally {
                isFetchingArticle = false
            }
        }
    }
}