package com.example.movies

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Poster(

    @SerializedName("url")
    @Expose
    val url: String) {

}