package com.aavidsoft.freshersbuddy.articles.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
@Entity(tableName = "articles")
data class Articles(
    var success : Boolean,
    var message : String,
    var data : ArrayList<Items>
)
data class Items(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var title : String,
    var body : String,
    var author: String,
    var publishedOn: String,
    var createdOn : String,
    var subCategoryId : Int,
    var mainCategoryId : Int,
    var tags: String,
    var status: Int


):Serializable
