package com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.models.InterviewQuestionItemDetails
import com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.repository.InterviewQuestionDetailsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InterviewQuestionDetailsViewModel(
    private val interviewQuestionDetailsRepository: InterviewQuestionDetailsRepository
) : ViewModel(){
    val interviewQuestionDetailStatusMutableLiveData = MutableLiveData<InterviewQuestionItemDetails>()

    fun fetchInterviewQuestionDetails(
        id : Int){
        CoroutineScope(Dispatchers.IO).launch {
            val interviewQuestionItemDetails = interviewQuestionDetailsRepository.fetchInterviewQuestionDetails(id)
            withContext(Dispatchers.Main){
                interviewQuestionDetailStatusMutableLiveData.postValue(interviewQuestionItemDetails)
            }
        }
    }
}