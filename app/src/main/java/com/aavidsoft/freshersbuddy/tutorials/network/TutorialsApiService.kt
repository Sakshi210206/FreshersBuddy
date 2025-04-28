package com.aavidsoft.freshersbuddy.tutorials.network

import com.aavidsoft.freshersbuddy.tutorials.models.ItemCategory
import com.aavidsoft.freshersbuddy.tutorials.models.ItemsTutorial
import com.aavidsoft.freshersbuddy.utils.apiresponse.ApiResponse
import com.aavidsoft.freshersbuddy.utils.apiservice.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface TutorialsApiService {
    @GET("categories")
    suspend fun fetchTutorialsCategory(
    ): ApiResponse.Success<ArrayList<ItemCategory>>

    @GET("tutorials/category/2/summaries")
    suspend fun fetchTutorials(
    ):ApiResponse.Success<ArrayList<ItemsTutorial>>

    companion object {
        private var tutorialsApiService: TutorialsApiService? = null
        fun getInstance(): TutorialsApiService {
            if (tutorialsApiService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                tutorialsApiService = retrofit.create(TutorialsApiService::class.java)
            }
            return tutorialsApiService!!
        }
    }
}


