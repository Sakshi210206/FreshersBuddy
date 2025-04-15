package com.aavidsoft.freshersbuddy.articles.allarticles.repositories

import com.aavidsoft.freshersbuddy.articles.allarticles.models.Items
import com.aavidsoft.freshersbuddy.articles.allarticles.network.ArticlesApiService
import com.aavidsoft.freshersbuddy.articles.utils.repository.Repository


class ArticleRepository (
    private val articleApiService : ArticlesApiService
):Repository(){

    suspend fun fetchItems(
        pageNo:Int,
        pageSize:Int
    ): ArrayList<Items>?{
           return articleApiService.fetchArticles(pageNo,pageSize).data
       }
}