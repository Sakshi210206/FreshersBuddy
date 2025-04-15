package com.aavidsoft.freshersbuddy.articles.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aavidsoft.freshersbuddy.articles.allarticles.models.ItemsDetail

import com.aavidsoft.freshersbuddy.databinding.ArticleDetailsFragmentBinding

class ArticleDetailsFragment : Fragment()
{
    private lateinit var articleDetailsFragmentBinding: ArticleDetailsFragmentBinding
    private var itemsDetail: ItemsDetail? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        articleDetailsFragmentBinding = ArticleDetailsFragmentBinding.inflate(layoutInflater)
        articleDetailsFragmentBinding.root.setOnClickListener { }
        if (arguments != null) {
            itemsDetail = requireArguments().getSerializable("items") as? ItemsDetail
            articleDetailsFragmentBinding.item = itemsDetail


        }
        return articleDetailsFragmentBinding.root
    }
}
