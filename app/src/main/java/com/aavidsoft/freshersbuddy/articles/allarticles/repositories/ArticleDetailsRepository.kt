package com.aavidsoft.freshersbuddy.articles.allarticles.repositories

import com.aavidsoft.freshersbuddy.articles.allarticles.models.ItemsDetail
import com.aavidsoft.freshersbuddy.articles.allarticles.network.ArticlesApiService
import com.aavidsoft.freshersbuddy.articles.utils.repository.Repository

class ArticleDetailsRepository(
    private val articleApiService: ArticlesApiService
) : Repository() {
    suspend fun fetchArticleDetails(id: Int): ItemsDetail {
        val response = articleApiService.fetchArticleDetails(id)
        return requireNotNull(response.data) {
            "Article detail response is null"
        }
    }
}