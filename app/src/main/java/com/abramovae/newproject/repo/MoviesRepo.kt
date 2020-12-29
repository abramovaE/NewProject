package com.abramovae.newproject.repo

import android.content.Context
import com.android.academy.fundamentals.homework.features.data.Movie
import com.android.academy.fundamentals.homework.features.data.loadMovies

interface IMoviesRepo{
    suspend fun getMovies(): List<Movie>
}

class MoviesRepo(private val context: Context): IMoviesRepo{
    override suspend fun getMovies(): List<Movie> {
        return loadMovies(context)
    }
}