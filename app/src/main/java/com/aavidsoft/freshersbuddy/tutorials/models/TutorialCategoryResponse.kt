package com.aavidsoft.freshersbuddy.tutorials.models

import java.io.Serializable

data class TutorialCategoryResponse(
    var success : Boolean,
    var message : String,
    val data: ArrayList<ItemCategory>
)
data class ItemCategory(
    var categoryId : Int,
    var name : String,
    var imageUrl : String
): Serializable

