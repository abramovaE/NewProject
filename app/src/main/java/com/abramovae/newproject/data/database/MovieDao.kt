package com.abramovae.newproject.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {
    @Query("SELECT * FROM moviedb")
    fun getAllMovies() : List<MovieDB>

    @Insert
    fun saveAllMovies(genres: List<MovieDB>)
}