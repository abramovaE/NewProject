package com.android.academy.fundamentals.homework.features.data

import android.os.Parcel
import android.os.Parcelable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlin.math.roundToInt

@Serializable
data class MovieTest(

//"genre_ids": [
//14,
//28,
//12
//],
//
//"popularity": 6017.605,
//"release_date": "2020-12-16",
//"video": false,
//"vote_count": 2153

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
        val genres: List<Int>,

        @Transient
        val actors: ArrayList<Actor> = ArrayList<Actor>()



)
{


}
