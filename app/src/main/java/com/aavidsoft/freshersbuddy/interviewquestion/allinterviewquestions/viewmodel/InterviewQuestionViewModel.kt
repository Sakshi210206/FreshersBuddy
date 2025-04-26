package com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.models.InterviewQuestion
import com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.repository.InterviewQuestionRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.ArrayList

class InterviewQuestionViewModel(
    private val interviewQuestionRepository: InterviewQuestionRepository
) : ViewModel() {
    val interviewQueUpdateAvailableLiveData = MutableLiveData<Boolean>()
    val interviewQuestion = ArrayList<InterviewQuestion>()
    var hasMoreData = true
    var isFetchingInterviewQue = false

    fun fetchInterviewQues() {

        if (!hasMoreData) {
            interviewQueUpdateAvailableLiveData.postValue(false)
            return
        }
        if (isFetchingInterviewQue) {
            return
        }
        isFetchingInterviewQue = true

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val interviewQues = interviewQuestionRepository.fetchInterviewQuestionList()
                if (interviewQues != null) {
                    withContext(Dispatchers.Main) {
                        this@InterviewQuestionViewModel.interviewQuestion.addAll(interviewQues)
                        interviewQueUpdateAvailableLiveData.postValue(true)
                            hasMoreData = false

                    }
                } else {
                    interviewQueUpdateAvailableLiveData.postValue(false)
                }
            } catch (e: Exception) {
                interviewQueUpdateAvailableLiveData.postValue(false)
            } finally {
                isFetchingInterviewQue = false
            }
        }
    }
}