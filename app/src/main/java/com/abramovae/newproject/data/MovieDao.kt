package com.abramovae.newproject.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.android.academy.fundamentals.homework.features.data.Movie

@Dao
interface MovieDao {

    @Insert
    fun insertAll(movies: List<Movie>)

    @Query("SELECT * FROM MOVIE")
    fun getAllMovies(): List<Movie>

}