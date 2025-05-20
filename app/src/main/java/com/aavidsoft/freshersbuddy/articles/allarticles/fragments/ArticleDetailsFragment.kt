package com.aavidsoft.freshersbuddy.articles.allarticles.fragments

import android.os.Bundle
import android.text.Html.escapeHtml
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aavidsoft.freshersbuddy.articles.allarticles.models.ItemsDetail
import com.aavidsoft.freshersbuddy.articles.allarticles.network.ArticlesApiService
import com.aavidsoft.freshersbuddy.articles.allarticles.repositories.ArticleDetailsRepository
import com.aavidsoft.freshersbuddy.articles.allarticles.viewmodels.ArticleDetailsViewModel
import com.aavidsoft.freshersbuddy.utils.factory.ViewModelFactory

import com.aavidsoft.freshersbuddy.databinding.ArticleDetailsFragmentBinding

class ArticleDetailsFragment : Fragment() {
    private lateinit var articleDetailsViewModel: ArticleDetailsViewModel
    private lateinit var articleDetailsFragmentBinding: ArticleDetailsFragmentBinding
    private var articleId: Int = 0
    private lateinit var itemsDetail: ItemsDetail

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
            articleId = arguments?.getInt("articleId", 0) ?: 0
        }

        articleDetailsViewModel.fetchArticleDetails(articleId)

        return articleDetailsFragmentBinding.root
    }

    private fun initViewModel() {
        articleDetailsViewModel = ViewModelProvider(
            this, ViewModelFactory(
                ArticleDetailsRepository(
                    ArticlesApiService.getInstance()
                )
            )
        )[ArticleDetailsViewModel::class.java]
    }
    private fun initObserver() {
        articleDetailsViewModel.articleDetailStatusMutableLiveData.observe(viewLifecycleOwner) { item ->
            itemsDetail = item
            articleDetailsFragmentBinding.item = item

            val imageUrl = item.imageUrls?.getOrNull(0) ?: ""
            val title = item.title ?: "No Title"
            val body = item.body ?: ""
            val author = item.author ?: "Unknown"
            val publishedOn = item.publishedOn ?: "Unknown"

            val htmlContent = """
            <html>
                <head>
                    <style>
                        body { font-family: sans-serif; padding: 16px; color: #000000; }
                        h1 { font-size: 45px; }
                        h2 { font-size: 30px;}
                        p,li{ font-size: 25px; }
                        img { width: 100%; height: auto; margin-bottom: 20px; }
                        .meta { font-size: 25px; color: #000000; margin-top: 20px; }
                        pre,code{ font-size: 25px;}
                    </style>
                </head>
                <body>
                    <img src="$imageUrl" alt="Article Image"/>
                    <h1>$title</h1>
                     $body
                    <p class="meta">Author: $author</p>
                    <p class="meta">Published on: $publishedOn</p>
                </body>
            </html>
        """.trimIndent()

            val webView = articleDetailsFragmentBinding.webView
            webView.settings.javaScriptEnabled = true
            webView.settings.loadWithOverviewMode = true
            webView.settings.useWideViewPort = true
            webView.settings.mixedContentMode = android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            webView.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null)
        }
    }
//    private fun initObserver() {
//        articleDetailsViewModel.articleDetailStatusMutableLiveData.observe(
//            viewLifecycleOwner
//        ) {
//           articleDetailsFragmentBinding.item = it
//            itemsDetail = it
//        }
//    }
}
