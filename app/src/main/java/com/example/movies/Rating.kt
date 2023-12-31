package com.example.movies

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Rating(

    @SerializedName("kp")
    @Expose
    val kp: Double?

)

    :Serializable

//    : Parcelable {
//    constructor(parcel: Parcel) : this(parcel.readValue(Double::class.java.classLoader) as? Double)
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeValue(kp)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<Rating> {
//        override fun createFromParcel(parcel: Parcel): Rating {
//            return Rating(parcel)
//        }
//
//        override fun newArray(size: Int): Array<Rating?> {
//            return arrayOfNulls(size)
//        }
//    }
//}