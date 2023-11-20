package com.example.movies

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Movie (

    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("description")
    @Expose
    val description: String,

    @SerializedName("year")
    @Expose
    val year: Int,

    @SerializedName("poster")
    @Expose
    val poster: Poster,

    @SerializedName("rating")
    @Expose
    val rating: Rating
)