package com.mindorks.bootcamp.demo.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GeneralResponse(
        @Expose
        @SerializedName("statusCode")
        var statusCode: String,

        @Expose
        @SerializedName("message")
        var message: String,

        @Expose
        @SerializedName("status")
        var status: Int
)