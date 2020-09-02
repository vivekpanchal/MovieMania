package com.mindorks.bootcamp.demo.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.panchal.vivek.moviemania.data.model.Post

data class PostListResponse(
        @Expose
        @SerializedName("statusCode")
        var statusCode: String,

        @Expose
        @SerializedName("message")
        var message: String,

        @Expose
        @SerializedName("data")
        val data: List<Post>
)