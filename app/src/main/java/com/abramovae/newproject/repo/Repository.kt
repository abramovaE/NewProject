package com.abramovae.newproject.repo

import android.content.Context
import com.android.academy.fundamentals.homework.features.data.Genre
import com.android.academy.fundamentals.homework.features.data.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(applicationContext: Context)
{

    private val db = AppDatabase.create(applicationContext)



    suspend fun getAllGenres(): List<Genre> = withContext(Dispatchers.IO) {
        db.genreDao.getAllGenres()
    }

    suspend fun getAllMovies(): List<Movie> = withContext(Dispatchers.IO) {
        db.movieDao.getAllMovies()
    }

}
