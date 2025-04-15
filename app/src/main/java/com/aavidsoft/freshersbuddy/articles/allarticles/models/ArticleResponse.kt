package com.aavidsoft.freshersbuddy.articles.allarticles.models

import java.io.Serializable

data class ArticleResponse(
    var success: Boolean,
    var message: String,
    var data: ArrayList<Items>
)

data class Items(
    var id: Int,
    var title: String,
    var author: String,
    var publishedOn: String,
    var createdOn: String,
    var imageUrl: String
) : Serializable

