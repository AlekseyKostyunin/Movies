package com.example.movies

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TrailerResponse(

    @SerializedName("videos")
    @Expose
    val trailerslist: TrailersList
)
