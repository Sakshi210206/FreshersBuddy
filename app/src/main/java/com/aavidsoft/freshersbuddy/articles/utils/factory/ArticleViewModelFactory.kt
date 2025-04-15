package com.aavidsoft.freshersbuddy.articles.utils.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aavidsoft.freshersbuddy.articles.allarticles.repositories.ArticleRepository
import com.aavidsoft.freshersbuddy.articles.allarticles.viewmodels.ArticleViewModel

class ArticleViewModelFactory (private val articleRepository: ArticleRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ArticleViewModel(articleRepository) as T
    }
}