package com.example.movies

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ReviewResponse(

    @SerializedName("docs")
    @Expose
    val reviews: List<Review>
)
