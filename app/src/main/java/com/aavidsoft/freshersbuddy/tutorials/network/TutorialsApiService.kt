package com.aavidsoft.freshersbuddy.tutorials.network

import com.aavidsoft.freshersbuddy.tutorials.models.ItemCategory
import com.aavidsoft.freshersbuddy.tutorials.models.ItemsTutorial
import com.aavidsoft.freshersbuddy.tutorials.models.TutorialsDetail
import com.aavidsoft.freshersbuddy.utils.apiresponse.ApiResponse
import com.aavidsoft.freshersbuddy.utils.apiservice.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface TutorialsApiService {
    @GET("categories")
    suspend fun fetchTutorialsCategory(
    ): ApiResponse.Success<ArrayList<ItemCategory>>

    @GET("tutorials/category/{id}/summaries")
    suspend fun fetchTutorials(
        @Path("id") id : Int
    ):ApiResponse.Success<ArrayList<ItemsTutorial>>

    @GET("tutorials/{id}")
    suspend fun fetchTutorialsDetails(
        @Path("id") id : Int
    ): ApiResponse.Success<TutorialsDetail>

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


