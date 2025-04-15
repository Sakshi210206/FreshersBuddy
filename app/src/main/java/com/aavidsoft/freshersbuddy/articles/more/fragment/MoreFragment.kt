package com.aavidsoft.freshersbuddy.articles.more.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aavidsoft.freshersbuddy.databinding.MoreFragmentMenuBinding

class MoreFragment : Fragment() {
    private lateinit var moreMenuBinding: MoreFragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        moreMenuBinding = MoreFragmentMenuBinding.inflate(layoutInflater,container,false)
        return moreMenuBinding.root
    }
}