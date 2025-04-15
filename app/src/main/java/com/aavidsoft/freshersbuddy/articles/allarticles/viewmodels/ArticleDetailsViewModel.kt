package com.aavidsoft.freshersbuddy.articles.allarticles.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aavidsoft.freshersbuddy.articles.allarticles.models.ItemsDetail
import com.aavidsoft.freshersbuddy.articles.allarticles.repositories.ArticleDetailsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import kotlinx.coroutines.withContext

class ArticleDetailsViewModel(
    private val articleDetailsRepository: ArticleDetailsRepository
) : ViewModel() {

//    val articleDetailUpdateAvailableLiveData = MutableLiveData<Boolean>(){
//        return
//    }
    val articleDetailStatusMutableLiveData = MutableLiveData<ItemsDetail>()


    fun fetchArticleDetails(
        id: Int
    ) {
        Log.e("tag", "------------------$id----------------------")
        CoroutineScope(Dispatchers.IO).launch {
            val itemsDetail = articleDetailsRepository.fetchArticleDetails(id)
            withContext(Dispatchers.Main) {
                articleDetailStatusMutableLiveData.postValue(itemsDetail)
            }
        }
    }
}
