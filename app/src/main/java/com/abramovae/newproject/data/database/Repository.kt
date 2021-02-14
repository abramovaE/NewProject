package com.abramovae.newproject.data.database

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.NotificationManagerCompat.IMPORTANCE_HIGH
import androidx.core.net.toUri
import com.abramovae.newproject.MainActivity
import com.abramovae.newproject.R
import com.abramovae.newproject.data.AppNotifications
import com.abramovae.newproject.data.Notifications
import com.android.academy.fundamentals.homework.features.data.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(applicationContext: Context)
{

    suspend fun showNotification(movie: Movie) = withContext(Dispatchers.IO){
        notifications.initialize()
        notifications.showNotification(movie)
    }

    private val db = AppDatabase.create(applicationContext)
    private val context = applicationContext;
    private val notifications: Notifications = AppNotifications(applicationContext)


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
        db.actorDao().getActors("%" + movieId + "%")
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
