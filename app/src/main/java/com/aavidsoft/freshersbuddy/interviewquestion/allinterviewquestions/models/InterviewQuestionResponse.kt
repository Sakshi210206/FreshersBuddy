package com.aavidsoft.freshersbuddy.interviewquestion.allinterviewquestions.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class InterviewQuestionResponse(
    var success : Boolean,
    var message : String,
    val data: ArrayList<InterviewQuestion>
)
data class InterviewQuestion(
    @SerializedName("interviewquestion_id")
    var interviewQuestionId : Int,
    var question : String,
    var tags : String,
    var status : Int
):Serializable
