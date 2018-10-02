package ru.app.a4rabetapp.models

import com.google.gson.annotations.SerializedName

data class ResultModel(@SerializedName("title")
                       val title: String,
                       @SerializedName("description")
                       val description: String,
                       @SerializedName("result")
                       val result: String,
                       @SerializedName("status")
                       val status: Int)

object ResultConstants {

    const val IN_PROGRESS = 0
    const val WIN = 1
    const val LOSS = 2
}