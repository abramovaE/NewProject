package com.abramovae.newproject.data.database

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(applicationContext: Context)
{
    private val db = AppDatabase.create(applicationContext)

    suspend fun getAllGenres(): List<GenreDB> = withContext(Dispatchers.IO) {
        db.genreDao().getAllGenres()
    }

    suspend fun getAllMovies(): List<MovieDB> = withContext(Dispatchers.IO) {
        db.movieDao().getAllMovies()
    }

    suspend fun saveAllGenres(genres:List<GenreDB>) = withContext(Dispatchers.IO){
        db.genreDao().saveAllGenres(genres)
    }

    suspend fun saveAllMovies(movies: List<MovieDB>) = withContext(Dispatchers.IO){
        db.movieDao().saveAllMovies(movies)
    }

    suspend fun getActors(movieId: Int): List<ActorDB> = withContext(Dispatchers.IO){
        db.actorDao().getActors(movieId)
    }

    suspend fun saveActors(actors: List<ActorDB>) = withContext(Dispatchers.IO){
        db.actorDao().saveActors(actors)
    }
}
