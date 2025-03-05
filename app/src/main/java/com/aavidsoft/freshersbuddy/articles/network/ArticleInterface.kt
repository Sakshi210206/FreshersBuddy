package com.aavidsoft.freshersbuddy.articles.network

import retrofit2.http.GET
import com.aavidsoft.freshersbuddy.articles.models.Articles
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface ArticleApiService {
    @GET("api/articles")
    suspend fun getArticles(): ArrayList<Articles>


    companion object RetrofitInstance{
        private const val BASE_URL = "https://xxbm4rsm-8080.inc1.devtunnels.ms/"
        fun getInstance(): ArticleApiService {
           val retrofit = Retrofit.Builder()
           val articlesServices=retrofit.baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ArticleApiService::class.java)
            return articlesServices
        }
    }
}

