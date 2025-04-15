package com.aavidsoft.freshersbuddy.articles.allarticles.network

import com.aavidsoft.freshersbuddy.articles.allarticles.models.ArticleDetailResponse
import com.aavidsoft.freshersbuddy.articles.allarticles.models.ArticleResponse
import com.aavidsoft.freshersbuddy.articles.allarticles.models.Items
import com.aavidsoft.freshersbuddy.articles.allarticles.models.ItemsDetail
import com.aavidsoft.freshersbuddy.articles.utils.apiresponse.ApiResponse
import com.aavidsoft.freshersbuddy.articles.utils.apiservice.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ArticlesApiService {
    @GET("articles/{pageNo}/{pageSize}")
    suspend fun fetchArticles(
        @Path("pageNo") pageNo: Int,
        @Path("pageSize") pageSize: Int
    ): ApiResponse.Success<ArrayList<Items>>

    @GET("articles/{id}")
    suspend fun fetchArticleDetails(
        @Path("id") id: Int
    ): ApiResponse.Success<ItemsDetail>

    companion object {
        private var articleApiService: ArticlesApiService? = null

        fun getInstance(): ArticlesApiService {
            if (articleApiService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                articleApiService = retrofit.create(ArticlesApiService::class.java)
            }
            return articleApiService!!
        }
    }
}

//https://xxbm4rsm-8080.inc1.devtunnels.ms/api/articles/1/5
//https://xxbm4rsm-8080.inc1.devtunnels.ms/api/articles/2