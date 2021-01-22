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

    suspend fun getActor(actorId: Int): ActorDB = withContext(Dispatchers.IO){
        db.actorDao().getActor(actorId)
    }


    suspend fun saveActors(actors: List<ActorDB>) = withContext(Dispatchers.IO){
        db.actorDao().saveActors(actors)
    }

    suspend fun getGenres(genresId: List<Int>) = withContext(Dispatchers.IO){
        db.genreDao().getGenres(genresId)
    }
}
