package com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class InterviewDetailResponse(
    var success : Boolean,
    var message : String,
    val data: InterviewQuestionItemDetails
)
data class InterviewQuestionItemDetails(
    @SerializedName("interviewquestion_id")
    var interviewQuestionId : Int,
    var question : String,
    var answer : String,
    var tags : String,
    var status : Int
):Serializable
