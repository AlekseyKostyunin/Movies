package com.example.movies

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TrailersList(

    @SerializedName("trailers")
    @Expose
    val trailers: List<Trailer>
)
