package com.aavidsoft.freshersbuddy.articles.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aavidsoft.freshersbuddy.R
import com.aavidsoft.freshersbuddy.articles.allarticles.adapters.ArticleAdapter
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
    private lateinit var articleAdapter: ArticleAdapter
    private lateinit var articlesBinding: ArticlesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        articlesBinding = ArticlesFragmentBinding.inflate(layoutInflater)

        initViews()
        initViewModel()
        initAdapters()
    //    initListeners()
        initObservers()

        articleViewModel.fetchArticles(page = 1, size = 5)
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
        articleAdapter = ArticleAdapter(articleViewModel.items)
        articlesBinding.recyclerSearch.adapter = articleAdapter
        articleAdapter.onItemClickListener = MyItemClickListener()
    }

    inner class MyItemClickListener : ArticleAdapter.OnItemClickListener {
        override fun onItemClick(items: Items, position: Int, articleAdapter: ArticleAdapter) {
            var articleDetailsFragment = ArticleDetailsFragment()
            val bundle = Bundle()
            bundle.putSerializable("items", items)
            articleDetailsFragment.arguments = bundle


            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main, articleDetailsFragment)
                .addToBackStack(null)
                .commit()
        }
    }


    private fun initListeners() {
        articlesBinding.recyclerSearch.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    if ((recyclerView.canScrollVertically(1)) && (newState == RecyclerView.SCROLL_STATE_IDLE)) {
                        articleViewModel.fetchArticles(page = 1, size = 5)
                    }
                }
            }
        )
    }

    private fun initViewModel() {
        articleViewModel = ViewModelProvider(
            this, ArticleViewModelFactory(
                ArticleRepository(
                    ArticlesApiService.getInstance()
                )
            )
        )[ArticleViewModel::class.java]
    }

    private fun initObservers() {
        articleViewModel.articleUpdateAvailableLiveData.observe(viewLifecycleOwner) {
            if (it) {
                articleAdapter.notifyDataSetChanged()
            }
        }
    }
}
