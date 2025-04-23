package com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.repository

import com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.models.InterviewQuestionItemDetails
import com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.network.InterviewQuestionApiService
import com.aavidsoft.freshersbuddy.utils.repository.Repository

class InterviewQuestionDetailsRepository(
    private val interviewQuestionApiService: InterviewQuestionApiService
):Repository(){
    suspend fun fetchInterviewQuestionDetails(id:Int) : InterviewQuestionItemDetails{
        val response = interviewQuestionApiService.fetchInterviewQuestionDetails(id)
        return  requireNotNull(response.data){
            "Interview Question detail response is null"
        }
    }
}