package com.abramovae.newproject.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GenreDao {

    @Query("SELECT * FROM genredb")
    fun getAllGenres() : List<GenreDB>

    @Query("SELECT * FROM genredb WHERE uid IN  (:genresId)")
    fun getGenres(genresId: List<Int>) : List<GenreDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllGenres(genres: List<GenreDB>)
}