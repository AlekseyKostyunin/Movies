package com.example.movies

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Poster(
    @SerializedName("url")
    @Expose
    val url: String?
)

    :Serializable

//    : Parcelable {
//    constructor(parcel: Parcel) : this(parcel.readString())
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeString(url)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<Poster> {
//        override fun createFromParcel(parcel: Parcel): Poster {
//            return Poster(parcel)
//        }
//
//        override fun newArray(size: Int): Array<Poster?> {
//            return arrayOfNulls(size)
//        }
//    }
//}

