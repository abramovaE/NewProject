package com.android.academy.fundamentals.homework.features.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlin.math.roundToInt

@Entity(tableName = "movie")
data class Movie(

    @PrimaryKey
    @ColumnInfo(name = "uid")
    @SerialName("id")
    val id: Int? = null,

    @ColumnInfo(name = "title")
    @SerialName("title")
    val title: String?,

    @ColumnInfo(name = "overview")
    @SerialName("overview")
    val overview: String?,

    @ColumnInfo(name = "poster_path")
    @SerialName("poster_path")
    val poster: String?,

    @ColumnInfo(name = "backdrop_path")
    @SerialName("backdrop_path")
    val backdrop: String?,

    @ColumnInfo(name = "vote_average")
    @SerialName("vote_average")
    val ratings: Float,

    @ColumnInfo(name = "adult")
    @SerialName("adult")
    val adult: Boolean,

    @ColumnInfo(name = "vote_count")
    @SerialName("vote_count")
    val runtime: Int,

    @SerialName("genre_ids")
    val genreIds: List<Int>,

    @Ignore
    var genres: List<Genre> = ArrayList<Genre>(),

    @Ignore
    var actors: List<Actor> = ArrayList<Actor>()
)
    : Parcelable{

    public constructor(): this(0, null, null, null,
        null, 0.0f, false, 0, emptyList(), emptyList(), emptyList())

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
        parcel.writeValue(id)
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
}