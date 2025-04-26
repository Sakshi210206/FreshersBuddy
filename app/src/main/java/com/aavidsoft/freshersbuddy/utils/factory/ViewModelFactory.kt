package com.aavidsoft.freshersbuddy.utils.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aavidsoft.freshersbuddy.articles.allarticles.repositories.ArticleDetailsRepository
import com.aavidsoft.freshersbuddy.articles.allarticles.repositories.ArticleRepository
import com.aavidsoft.freshersbuddy.articles.allarticles.viewmodels.ArticleDetailsViewModel
import com.aavidsoft.freshersbuddy.articles.allarticles.viewmodels.ArticleViewModel
import com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.repository.InterviewQuestionDetailsRepository
import com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.repository.InterviewQuestionRepository
import com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.viewmodel.InterviewQuestionDetailsViewModel
import com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.viewmodel.InterviewQuestionViewModel
import com.aavidsoft.freshersbuddy.utils.repository.Repository

class ViewModelFactory(
    private val repository: Repository
     ):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ArticleViewModel::class.java) && repository is ArticleRepository) {
            return ArticleViewModel(repository) as T
        }

        if (modelClass.isAssignableFrom(ArticleDetailsViewModel::class.java) && repository is ArticleDetailsRepository) {
            return ArticleDetailsViewModel(repository) as T
        }

        if(modelClass.isAssignableFrom(InterviewQuestionViewModel::class.java) && repository is InterviewQuestionRepository){
            return InterviewQuestionViewModel(repository) as T
        }

        if(modelClass.isAssignableFrom(InterviewQuestionDetailsViewModel::class.java) && repository is InterviewQuestionDetailsRepository){
            return InterviewQuestionDetailsViewModel(repository) as T
        }

        throw Exception("Unable to create vie model")
    }
}