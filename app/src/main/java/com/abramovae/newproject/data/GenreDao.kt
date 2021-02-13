package com.abramovae.newproject.data

import androidx.room.Dao
import androidx.room.Query
import com.android.academy.fundamentals.homework.features.data.Genre

@Dao
interface GenreDao {

    @Query("SELECT * FROM genre")
    fun getAllGenres(): List<Genre>
}