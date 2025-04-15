package com.aavidsoft.freshersbuddy.articles.profile.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aavidsoft.freshersbuddy.databinding.ProfileFragmentMenuBinding

class ProfileFragment : Fragment() {
    private lateinit var profileMenuBinding: ProfileFragmentMenuBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        profileMenuBinding = ProfileFragmentMenuBinding.inflate(layoutInflater,container,false)
        return profileMenuBinding.root
    }
}