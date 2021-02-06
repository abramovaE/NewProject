package com.android.academy.fundamentals.homework.features.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "actor")
data class Actor(

    @SerialName("id")
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    val id: Int,

    @SerialName("name")
    @ColumnInfo(name = "name")
    val name: String?,

    @SerialName("profile_path")
    @ColumnInfo(name = "profile_path")
    val picture: String?

): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(picture)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Actor> {
        override fun createFromParcel(parcel: Parcel): Actor {
            return Actor(parcel)
        }

        override fun newArray(size: Int): Array<Actor?> {
            return arrayOfNulls(size)
        }
    }
}