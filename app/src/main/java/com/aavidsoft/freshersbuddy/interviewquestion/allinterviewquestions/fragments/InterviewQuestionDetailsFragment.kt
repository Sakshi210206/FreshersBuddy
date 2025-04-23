package com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aavidsoft.freshersbuddy.databinding.InterviewQuestionDetailsFragmentBinding
import com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.models.InterviewQuestionItemDetails
import com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.network.InterviewQuestionApiService
import com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.repository.InterviewQuestionDetailsRepository
import com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.viewmodel.InterviewQuestionDetailsViewModel
import com.aavidsoft.freshersbuddy.utils.factory.ViewModelFactory

class InterviewQuestionDetailsFragment : Fragment() {
    private lateinit var interviewQuestionDetailsViewModel: InterviewQuestionDetailsViewModel
    private lateinit var interviewQuestionDetailsFragmentBinding: InterviewQuestionDetailsFragmentBinding
    private var interviewQuestionId : Int = 0
    private lateinit var interviewQuestionItemDetails: InterviewQuestionItemDetails

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initViewModel()
        initObserver()
        interviewQuestionDetailsFragmentBinding = InterviewQuestionDetailsFragmentBinding.inflate(layoutInflater)
        interviewQuestionDetailsFragmentBinding.root.setOnClickListener {}
        if(arguments != null){
            interviewQuestionId = arguments?.getInt("interviewQuestionId",0) ?: 0
        }
        interviewQuestionDetailsViewModel.fetchInterviewQuestionDetails(interviewQuestionId)
        return interviewQuestionDetailsFragmentBinding.root
    }
    private fun initViewModel(){
        interviewQuestionDetailsViewModel = ViewModelProvider(
            this,ViewModelFactory(
                InterviewQuestionDetailsRepository(
                    InterviewQuestionApiService.getInstance()
                )
            )
        )[InterviewQuestionDetailsViewModel::class.java]
    }
    private fun initObserver(){
        interviewQuestionDetailsViewModel.interviewQuestionDetailStatusMutableLiveData.observe(
            viewLifecycleOwner
        ){
            interviewQuestionDetailsFragmentBinding.interviewQuestionDetailItems = it
            interviewQuestionItemDetails = it
        }
    }


}