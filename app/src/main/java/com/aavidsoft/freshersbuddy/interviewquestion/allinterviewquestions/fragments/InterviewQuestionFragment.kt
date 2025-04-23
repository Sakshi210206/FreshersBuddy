package com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.fragments

import InterviewQuestionAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aavidsoft.freshersbuddy.databinding.InterviewQuestionsFragmentBinding
import com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.network.InterviewQuestionApiService
import com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.repository.InterviewQuestionRepository
import com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.viewmodel.InterviewQuestionViewModel
import com.aavidsoft.freshersbuddy.utils.factory.ViewModelFactory

class InterviewQuestionFragment : Fragment() {

    private lateinit var interviewQuestionsFragmentBinding: InterviewQuestionsFragmentBinding
    private lateinit var interviewQuestionViewModel: InterviewQuestionViewModel
    private lateinit var interviewQuestionAdapter: InterviewQuestionAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        interviewQuestionsFragmentBinding =
            InterviewQuestionsFragmentBinding.inflate(layoutInflater,container,false)
        initViews()
        initViewModel()
        initAdapter()
        initListener()
        initObservers()

        interviewQuestionViewModel.fetchInterviewQues()
        return interviewQuestionsFragmentBinding.root
    }

    private fun initViews() {
        interviewQuestionsFragmentBinding.recyclerSearchInterviewQue.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val toolbar = interviewQuestionsFragmentBinding.interviewQuestionToolbar.toolbar
        toolbar.title = "Interview Questions"

    }

    private fun initAdapter() {
        interviewQuestionAdapter = InterviewQuestionAdapter(interviewQuestionViewModel.interviewQuestion)
        interviewQuestionsFragmentBinding.recyclerSearchInterviewQue.adapter = interviewQuestionAdapter
    }

    private fun initListener() {
        interviewQuestionsFragmentBinding.recyclerSearchInterviewQue.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            private var isScrollingUp: Boolean = false
            private var isScrollingDown: Boolean = false
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                isScrollingUp = dy > 0
                isScrollingDown = dy <= -1
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount
                if (isScrollingDown) {
                    if (!interviewQuestionViewModel.isFetchingInterviewQue) {
                        if (interviewQuestionViewModel.hasMoreData) {
                            interviewQuestionViewModel.fetchInterviewQues()
                        }
                    }
                } else if (isScrollingUp && lastVisibleItemPosition == totalItemCount - 1) {
                    if (!interviewQuestionViewModel.isFetchingInterviewQue) {
                        if (interviewQuestionViewModel.hasMoreData) {
                            interviewQuestionViewModel.fetchInterviewQues()

                        }
                    }
                }
            }
        }
        )
    }
    private fun initViewModel(){
          interviewQuestionViewModel = ViewModelProvider(
              this, ViewModelFactory(
                  InterviewQuestionRepository(
              InterviewQuestionApiService.getInstance()
                  )
              )
          )[InterviewQuestionViewModel::class.java]
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun initObservers(){
       interviewQuestionViewModel.interviewQueUpdateAvailableLiveData.observe(viewLifecycleOwner){
           if(it){
               interviewQuestionAdapter.notifyDataSetChanged()
           }
       }
    }
}
