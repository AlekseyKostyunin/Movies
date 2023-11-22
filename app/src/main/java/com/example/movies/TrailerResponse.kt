package com.example.movies

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TrailerResponse(

    @SerializedName("docs")
    @Expose
//    val trailerslist: List<TrailersList>
    val videosList: List<VideosList>
)
