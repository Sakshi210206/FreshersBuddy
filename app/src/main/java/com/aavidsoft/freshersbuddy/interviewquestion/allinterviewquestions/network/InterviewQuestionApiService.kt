package com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.network

import com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.models.InterviewQuestion
import com.aavidsoft.freshersbuddy.utils.apiresponse.ApiResponse
import com.aavidsoft.freshersbuddy.utils.apiservice.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface InterviewQuestionApiService {


    companion object {
        private var interviewQuestionApiService: InterviewQuestionApiService? = null

        fun getInstance(): InterviewQuestionApiService {
            if (interviewQuestionApiService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                interviewQuestionApiService = retrofit.create(InterviewQuestionApiService::class.java)
            }
            return interviewQuestionApiService!!
        }
    }
}
//https://xxbm4rsm-8080.inc1.devtunnels.ms/api/interviewquestions