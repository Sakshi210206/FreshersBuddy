package com.aavidsoft.freshersbuddy.tutorials.models

import java.io.Serializable

data class TutorialDetailResponse(
 var success: Boolean,
 var message: String,
 var data: TutorialsDetail
)
data class TutorialsDetail(
   var id : Int,
   var title : String,
   var body : String,
   var author : String,
   var publishedOn : String,
   var  createdOn : String,
   var categoryId : Int,
   var tags : String,
   var status : Int,
   var imageUrls: ArrayList<String>
):Serializable