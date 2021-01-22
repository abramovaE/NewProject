package com.abramovae.newproject.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity(tableName = "moviedb")
class MovieDB (

    @PrimaryKey
    @ColumnInfo(name = "uid")
    var uid: Int,

    @ColumnInfo(name = "title")
    var title: String?,

    @ColumnInfo(name = "overview")
    var overview: String?,

    @ColumnInfo(name = "poster_path")
    var poster: String?,

    @ColumnInfo(name = "backdrop_path")
    var backdrop: String?,

    @ColumnInfo(name = "vote_average")
    var ratings: Float,

    @ColumnInfo(name = "adult")
    var adult: Boolean,

    @ColumnInfo(name = "vote_count")
    var runtime: Int,

    @ColumnInfo(name = "genre_ids")
    var genreIds: List<Int>

){

}