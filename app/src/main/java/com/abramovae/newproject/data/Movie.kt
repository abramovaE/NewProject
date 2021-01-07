package com.android.academy.fundamentals.homework.features.data

import android.os.Parcel
import android.os.Parcelable
import kotlin.math.roundToInt

data class Movie(
    val id: Int,
    val title: String?,
    val overview: String?,
    val poster: String?,
    val backdrop: String?,
    val ratings: Float,
    val adult: Boolean,
    val runtime: Int,
    val genres: List<Genre>,
    val actors: List<Actor>): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readFloat(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.createTypedArrayList(Genre)!!,
        parcel.createTypedArrayList(Actor)!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(overview)
        parcel.writeString(poster)
        parcel.writeString(backdrop)
        parcel.writeFloat(ratings)
        parcel.writeByte(if (adult) 1 else 0)
        parcel.writeInt(runtime)
        parcel.writeTypedList(genres)
        parcel.writeTypedList(actors)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }


    fun getRating(): Int{

        return ((ratings/2.0).roundToInt());
    }


}