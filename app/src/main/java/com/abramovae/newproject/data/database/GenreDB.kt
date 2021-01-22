package com.abramovae.newproject.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity(tableName = "genredb")
class GenreDB(

    @PrimaryKey
    @ColumnInfo(name = "uid")
    val uid: Int,

    @ColumnInfo(name= "name")
    val name: String?
) {

}