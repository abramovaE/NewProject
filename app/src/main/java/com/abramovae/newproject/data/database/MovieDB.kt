package com.abramovae.newproject.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity(tableName = "moviedb")
class MovieDB (

    @PrimaryKey
    @ColumnInfo(name = "uid")
    val uid: Int,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "overview")
    val overview: String?,

    @ColumnInfo(name = "poster_path")
    val poster: String?,

    @ColumnInfo(name = "backdrop_path")
    val backdrop: String?,

    @ColumnInfo(name = "vote_average")
    val ratings: Float,

    @ColumnInfo(name = "adult")
    val adult: Boolean,

    @ColumnInfo(name = "vote_count")
    val runtime: Int,

    @ColumnInfo(name = "genre_ids")
    val genreIds: List<Int>
){

}