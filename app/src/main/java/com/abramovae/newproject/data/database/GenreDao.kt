package com.abramovae.newproject.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GenreDao {

    @Query("SELECT * FROM genredb")
    fun getAllGenres() : List<GenreDB>

    @Insert
    fun saveAllGenres(genres: List<GenreDB>)
}