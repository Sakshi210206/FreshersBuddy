package com.aavidsoft.freshersbuddy.articles.allarticles.repositories

import com.aavidsoft.freshersbuddy.articles.allarticles.models.Items
import com.aavidsoft.freshersbuddy.articles.allarticles.models.ItemsDetail
import com.aavidsoft.freshersbuddy.articles.allarticles.network.ArticlesApiService


class ArticleRepository (private val articleApiService : ArticlesApiService){
       suspend fun fetchItems(page:Int,size:Int): ArrayList<Items>?{
           return articleApiService.fetchArticles(page,size).data
       }
    suspend fun  fetchItemDetails(id:Int):ArrayList<ItemsDetail>?{
        return articleApiService.fetchDetailArticle(id).data
    }
}