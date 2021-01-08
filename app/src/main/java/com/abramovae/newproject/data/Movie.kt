package com.android.academy.fundamentals.homework.features.data

import android.os.Parcel
import android.os.Parcelable
import androidx.versionedparcelable.NonParcelField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.math.roundToInt

@Serializable
data class Movie(


        @SerialName("id")
    val id: Int,

        @SerialName("title")
    val title: String?,

        @SerialName("overview")
    val overview: String?,

        @SerialName("poster_path")
    val poster: String?,

        @SerialName("backdrop_path")
    val backdrop: String?,

        @SerialName("vote_average")
    val ratings: Float,

        @SerialName("adult")
    val adult: Boolean,

        @SerialName("vote_count")
    val runtime: Int,

        @SerialName("genre_ids")
    val genreIds: List<Int>,

        var genres: List<Genre> = ArrayList<Genre>(),

        var actors: List<Actor> = ArrayList<Actor>()
): Parcelable{



    constructor(parcel: Parcel) : this(



        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readFloat(),
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
            parcel.createIntArray()!!.toList(),

        parcel.createTypedArrayList(Genre)!!,
        parcel.createTypedArrayList(Actor)!!
    ) {
    }


    fun getRating(): Int{
        return ((ratings/2.0).roundToInt());
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
        parcel.writeIntArray(genreIds.toIntArray())
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



}