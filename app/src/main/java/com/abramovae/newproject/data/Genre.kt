package com.android.academy.fundamentals.homework.features.data

import android.os.Parcel
import android.os.Parcelable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Genre(

    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String?
): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return name.toString();
    }

    companion object CREATOR : Parcelable.Creator<Genre> {
        override fun createFromParcel(parcel: Parcel): Genre {
            return Genre(parcel)
        }

        override fun newArray(size: Int): Array<Genre?> {
            return arrayOfNulls(size)
        }
    }



}