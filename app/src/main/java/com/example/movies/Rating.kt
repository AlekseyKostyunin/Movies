package com.example.movies

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Rating(

    @SerializedName("kp")
    @Expose
    val kp: Double) {

}