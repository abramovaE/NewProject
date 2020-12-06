package com.abramovae.newproject

import android.os.Parcel
import android.os.Parcelable


class Movie(val title: String, val genre: String, val dur: Int, val reviews: Int, val imgId: Int, val age: Int, val stars: Int) : Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readString().toString(),
            parcel.readString().toString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(genre)
        parcel.writeInt(dur)
        parcel.writeInt(reviews)
        parcel.writeInt(imgId)
        parcel.writeInt(age)
        parcel.writeInt(stars)
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