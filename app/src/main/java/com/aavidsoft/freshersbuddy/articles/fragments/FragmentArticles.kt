package com.aavidsoft.freshersbuddy.articles.fragments



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.aavidsoft.freshersbuddy.R
import com.aavidsoft.freshersbuddy.articles.adapters.ArticleAdapter


class FragmentArticles : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchEditText: EditText
    private lateinit var articleAdapter: ArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_articles, container, false)
    }
}
/*
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerSearch)
        searchEditText = view.findViewById(R.id.searchEdit)

        articleAdapter = ArticleAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = articleAdapter

        articleViewModel.articles.observe(viewLifecycleOwner) { articles ->
            articleAdapter.updateArticles(articles)
        }

        articleViewModel.fetchArticles()
    }
}
*/