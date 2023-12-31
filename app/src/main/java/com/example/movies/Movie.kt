package com.example.movies

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "favourite_movies")
data class Movie (

    @PrimaryKey
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
    @Embedded
    val poster: Poster,

    @SerializedName("rating")
    @Expose
    @Embedded
    val rating: Rating

)
    : Serializable

//    : Parcelable {
//    constructor(parcel: Parcel) : this(
//        parcel.readInt(),
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readInt(),
////        parcel.readString().toString(),
////        parcel.readInt().toString(),
//        Poster.createFromParcel(parcel),
//        Rating.createFromParcel(parcel)
////        TODO("poster"),
////        TODO("rating")
//    )
//
//    override fun describeContents(): Int {
//        TODO("Not yet implemented")
//    }
//
//    override fun writeToParcel(dest: Parcel, flags: Int) {
//        TODO("Not yet implemented")
//    }
//
//    companion object CREATOR : Parcelable.Creator<Movie> {
//        override fun createFromParcel(parcel: Parcel): Movie {
//            return Movie(parcel)
//        }
//
//        override fun newArray(size: Int): Array<Movie?> {
//            return arrayOfNulls(size)
//        }
//    }
//}

