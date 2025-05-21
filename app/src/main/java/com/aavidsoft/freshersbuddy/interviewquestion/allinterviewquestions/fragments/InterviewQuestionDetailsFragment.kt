package com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.fragments

import android.os.Bundle
import android.text.Html.escapeHtml
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
    private fun initObserver() {
        interviewQuestionDetailsViewModel.interviewQuestionDetailStatusMutableLiveData.observe(
            viewLifecycleOwner
        ) { item ->
            interviewQuestionDetailsFragmentBinding.interviewQuestionDetailItems = item
            interviewQuestionItemDetails = item

            val question = item.question ?: "No Question Available"
            val answer = item.answer ?: "No Answer Available"

            val htmlContent = """
            <html>
                <head>
                    <style>
                        body { font-family: sans-serif; padding: 16px; color: #000000; }
                        h1 { font-size: 45px; color: #0F172A; }
                        p { font-size: 30px; line-height: 1.6; color: #1E293B; }
                    </style>
                </head>
                <body>
                    <h1>${escapeHtml(question)}</h1>
                    <p>${escapeHtml(answer)}</p>
                </body>
            </html>
        """.trimIndent()

            interviewQuestionDetailsFragmentBinding.webView.apply {
                settings.javaScriptEnabled = true
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.mixedContentMode = android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null)
            }
        }
    }
}