package com.aavidsoft.freshersbuddy.articles.allarticles.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aavidsoft.freshersbuddy.articles.allarticles.models.ItemsDetail
import com.aavidsoft.freshersbuddy.articles.allarticles.network.ArticlesApiService
import com.aavidsoft.freshersbuddy.articles.allarticles.repositories.ArticleDetailsRepository
import com.aavidsoft.freshersbuddy.articles.allarticles.viewmodels.ArticleDetailsViewModel
import com.aavidsoft.freshersbuddy.articles.utils.factory.ArticleViewModelFactory

import com.aavidsoft.freshersbuddy.databinding.ArticleDetailsFragmentBinding

class ArticleDetailsFragment : Fragment() {
    private lateinit var articleDetailsViewModel: ArticleDetailsViewModel
    private lateinit var articleDetailsFragmentBinding: ArticleDetailsFragmentBinding
    private var articleId : Int = 0
    private  lateinit var  itemsDetail: ItemsDetail

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initViewModel()
        initObserver()
        articleDetailsFragmentBinding = ArticleDetailsFragmentBinding.inflate(layoutInflater)
        articleDetailsFragmentBinding.root.setOnClickListener { }
        if (arguments != null) {
             articleId = arguments?.getInt("articleId",0)?:0
        }

        articleDetailsViewModel.fetchArticleDetails(articleId)

        return articleDetailsFragmentBinding.root
    }

    private fun initViewModel() {
        articleDetailsViewModel = ViewModelProvider(
            this, ArticleViewModelFactory(
                ArticleDetailsRepository(
                    ArticlesApiService.getInstance()
                )
            )
        )[ArticleDetailsViewModel::class.java]
    }

    private fun initObserver(){
        articleDetailsViewModel.articleDetailStatusMutableLiveData.observe(
            viewLifecycleOwner
        ) {
            articleDetailsFragmentBinding.item = it
            itemsDetail = it
        }
    }
}
