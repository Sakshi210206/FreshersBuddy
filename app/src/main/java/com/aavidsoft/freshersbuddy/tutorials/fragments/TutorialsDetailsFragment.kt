package com.aavidsoft.freshersbuddy.tutorials.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aavidsoft.freshersbuddy.databinding.TutorialDetailsFragmentBinding
import com.aavidsoft.freshersbuddy.tutorials.models.TutorialsDetail
import com.aavidsoft.freshersbuddy.tutorials.network.TutorialsApiService
import com.aavidsoft.freshersbuddy.tutorials.repository.TutorialsDetailsRepository
import com.aavidsoft.freshersbuddy.tutorials.viewmodel.TutorialsDetailsViewModel
import com.aavidsoft.freshersbuddy.utils.factory.ViewModelFactory

class TutorialsDetailsFragment : Fragment() {
    private lateinit var tutorialsDetailsViewModel: TutorialsDetailsViewModel
    private lateinit var tutorialsDetailsFragment: TutorialDetailsFragmentBinding
    private var tutorialId : Int = 0
    private lateinit var tutorialDetails: TutorialsDetail

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initViewModel()
        initObserver()
         tutorialsDetailsFragment = TutorialDetailsFragmentBinding.inflate(layoutInflater)
        tutorialsDetailsFragment.root.setOnClickListener{}
        if(arguments!= null){
           tutorialId = arguments?.getInt("tutorialId") ?: 0
        }

        tutorialsDetailsViewModel.fetchTutorialDetails(tutorialId)
        return tutorialsDetailsFragment.root
    }
    private fun initViewModel(){
        tutorialsDetailsViewModel = ViewModelProvider(
            this,ViewModelFactory(
                TutorialsDetailsRepository(
                    TutorialsApiService.getInstance()
                )
            )
        )[TutorialsDetailsViewModel::class.java]
    }
    private fun initObserver(){
        tutorialsDetailsViewModel.tutorialDetailStatusMutableLiveData.observe(
            viewLifecycleOwner
        ){
            tutorialsDetailsFragment.tutorialDetailsItem = it
            tutorialDetails = it
        }
    }
}