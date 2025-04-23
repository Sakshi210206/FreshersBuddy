package com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.repository

import com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.models.InterviewQuestion
import com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.network.InterviewQuestionApiService
import com.aavidsoft.freshersbuddy.utils.repository.Repository

class InterviewQuestionRepository(
    private val interviewQuestionApiService: InterviewQuestionApiService
) : Repository() {

    suspend fun fetchInterviewQueList(): ArrayList<InterviewQuestion>? {
        return interviewQuestionApiService.fetchInterviewQuestions().data
    }
}