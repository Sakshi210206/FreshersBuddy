package com.aavidsoft.freshersbuddy.tutorials.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aavidsoft.freshersbuddy.databinding.TutorialsBinding
import com.aavidsoft.freshersbuddy.tutorials.adapter.TutorialsAdapter
import com.aavidsoft.freshersbuddy.tutorials.viewmodel.TutorialsViewModel

class Tutorialsfragment : Fragment() {
    private lateinit var tutorialsAdapter: TutorialsAdapter
    private lateinit var  tutorialsViewModel: TutorialsViewModel
    private lateinit var  tutorialsFragmentBinding: TutorialsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

}