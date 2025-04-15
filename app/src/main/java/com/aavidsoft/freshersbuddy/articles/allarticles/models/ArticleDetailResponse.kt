package com.aavidsoft.freshersbuddy.articles.allarticles.models

import java.io.Serializable

data class ArticleDetailResponse(
    var success : Boolean,
    var message : String,
    var data : ArrayList<ItemsDetail>
)
data class ItemsDetail(
    var id : Int,
    var title : String,
    var body : String,
    var author: String,
    var publishedOn: String,
    var createdOn : String,
    var subCategoryId:Int,
    var mainCategory: Int,
    var tags:String,
    var status: Int
):Serializable


