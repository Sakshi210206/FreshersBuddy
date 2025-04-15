package com.aavidsoft.freshersbuddy.articles.allarticles.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aavidsoft.freshersbuddy.R
import com.aavidsoft.freshersbuddy.articles.allarticles.adapters.ArticlesAdapter
import com.aavidsoft.freshersbuddy.articles.allarticles.models.Items
import com.aavidsoft.freshersbuddy.articles.utils.factory.ArticleViewModelFactory
import com.aavidsoft.freshersbuddy.articles.allarticles.network.ArticlesApiService
import com.aavidsoft.freshersbuddy.articles.allarticles.repositories.ArticleRepository
import com.aavidsoft.freshersbuddy.articles.allarticles.viewmodels.ArticleViewModel
import com.aavidsoft.freshersbuddy.articles.home.fragment.HomeFragment
import com.aavidsoft.freshersbuddy.articles.more.fragment.MoreFragment
import com.aavidsoft.freshersbuddy.articles.profile.fragment.ProfileFragment
import com.aavidsoft.freshersbuddy.databinding.ArticlesFragmentBinding


class ArticlesFragment : Fragment() {

    private lateinit var articleViewModel: ArticleViewModel
    private lateinit var articlesAdapter: ArticlesAdapter
    private lateinit var articlesBinding: ArticlesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        articlesBinding = ArticlesFragmentBinding.inflate(layoutInflater)

        initViews()
        initViewModel()
        initAdapters()
        initListeners()
        initObservers()

        articleViewModel.fetchArticles()
        return articlesBinding.root
    }

    @SuppressLint("ResourceType")
    private fun initViews() {
        articlesBinding.recyclerSearch.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val menuInflater = MenuInflater(context)
        menuInflater.inflate(R.menu.article_menu, articlesBinding.toolbar.menu)

        articlesBinding.toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.more -> {
                    replaceFragment(MoreFragment())
                    true
                }

                R.id.profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }

                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main, fragment)
            .addToBackStack(null)
            .commit()

    }

    private fun initAdapters() {
        articlesAdapter = ArticlesAdapter(articleViewModel.items)
        articlesBinding.recyclerSearch.adapter = articlesAdapter
    }

    private fun initListeners() {
        articlesBinding.recyclerSearch.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                private var isScrollingUp: Boolean = false
                private var isScrollingDown: Boolean = false
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    isScrollingUp = dy > 0
                    isScrollingDown = dy <= -1
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                    val totalItemCount = layoutManager.itemCount

                    if (isScrollingDown) {
                        if (!articleViewModel.isFetchingArticle) {
                            if (articleViewModel.hasMoreData) {
                                articleViewModel.fetchArticles()
                            }
                        }
                    } else if (isScrollingUp && lastVisibleItemPosition == totalItemCount - 1) {
                        if (!articleViewModel.isFetchingArticle) {
                            if (articleViewModel.hasMoreData) {
                                articleViewModel.fetchArticles()

                            }
                        }
                    }

                }
            }
        )

        articlesAdapter.onItemsClickListener = object : ArticlesAdapter.OnItemsClickListener {
            override fun onItemsClick(
                items: Items,
                position: Int,
                articlesAdapter: ArticlesAdapter
            ) {
                showArticleDetails(items)
            }
        }
    }

    private fun showArticleDetails(items: Items) {
        val articleDetailsFragment = ArticleDetailsFragment()
        var bundle = Bundle()
        bundle.putSerializable("articleId", items.id)
        articleDetailsFragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .add(R.id.main, articleDetailsFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun initViewModel() {
        articleViewModel = ViewModelProvider(
            this,
            ArticleViewModelFactory(
                ArticleRepository(
                    ArticlesApiService.getInstance()
                )
            )
        )[ArticleViewModel::class.java]
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initObservers() {
        articleViewModel.articleUpdateAvailableLiveData.observe(viewLifecycleOwner) {
            if (it) {
                articlesAdapter.notifyDataSetChanged()
                Log.e("vishal", "data set updated: len ${articleViewModel.items.size}")
            }
        }
    }
}
