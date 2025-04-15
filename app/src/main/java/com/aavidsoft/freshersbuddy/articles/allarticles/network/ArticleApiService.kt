package com.aavidsoft.freshersbuddy.articles.allarticles.network

import com.aavidsoft.freshersbuddy.articles.allarticles.models.ArticleDetailResponse
import com.aavidsoft.freshersbuddy.articles.allarticles.models.ArticleResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ArticlesApiService {
    @GET("articles/{page}/{size}")
    suspend fun fetchArticles( @Path("page")page:Int,@Path("size")size:Int): ArticleResponse

    @GET("articles/{id}")
    suspend fun fetchDetailArticle(@Path("id")id:Int):ArticleDetailResponse

    companion object{
        private var articleApiService: ArticlesApiService? = null

        fun getInstance(): ArticlesApiService {
            if(articleApiService == null){
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://xxbm4rsm-8080.inc1.devtunnels.ms/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                articleApiService =retrofit.create(ArticlesApiService::class.java)
            }
            return articleApiService!!
        }
    }
}

//https://xxbm4rsm-8080.inc1.devtunnels.ms/api/articles/1/5
//https://xxbm4rsm-8080.inc1.devtunnels.ms/api/articles/2