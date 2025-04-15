package com.aavidsoft.freshersbuddy.articles.home.fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.aavidsoft.freshersbuddy.databinding.HomeFragmentMenuBinding

class HomeFragment : Fragment() {
    private lateinit var homeMenuBinding: HomeFragmentMenuBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeMenuBinding = HomeFragmentMenuBinding.inflate(layoutInflater,container,false)
        return homeMenuBinding.root
    }
}