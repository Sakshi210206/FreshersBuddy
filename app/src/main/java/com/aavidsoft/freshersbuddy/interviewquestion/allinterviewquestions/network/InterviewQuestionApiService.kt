package com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.network

import com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.models.InterviewQuestion
import com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.models.InterviewQuestionItemDetails
import com.aavidsoft.freshersbuddy.utils.apiresponse.ApiResponse
import com.aavidsoft.freshersbuddy.utils.apiservice.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface InterviewQuestionApiService {
    @GET("interviewquestions/summary")
    suspend fun fetchInterviewQuestions()
    :ApiResponse.Success<ArrayList<InterviewQuestion>>
    @GET("interviewquestions/{interviewQuestionId}")
    suspend fun fetchInterviewQuestionDetails(
        @Path("interviewQuestionId")id:Int
    ):ApiResponse.Success<InterviewQuestionItemDetails>


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
//https://xxbm4rsm-8080.inc1.devtunnels.ms/api/interviewquestions/1