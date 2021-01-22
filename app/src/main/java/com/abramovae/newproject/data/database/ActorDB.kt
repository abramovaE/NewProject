package com.abramovae.newproject.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity(tableName = "actordb")
class ActorDB(
    @PrimaryKey
    @ColumnInfo(name= "uid")
    var uid: Int? = null,

    @ColumnInfo(name= "name")
    var name: String?,

    @ColumnInfo(name= "profile_path")
    var picture: String?
) {
}