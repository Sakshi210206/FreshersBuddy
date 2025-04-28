package com.aavidsoft.freshersbuddy.tutorials.models

import java.io.Serializable

data class TutorialResponse(
    var success : Boolean,
    var message : String,
    val data: ArrayList<ItemsTutorial>
)
data class ItemsTutorial(
    var id : Int,
    var title : String,
    var tags : String,
    var status : Int
):Serializable