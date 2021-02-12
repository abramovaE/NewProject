package com.abramovae.newproject.data.service

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.abramovae.newproject.data.RetrofitModule
import com.abramovae.newproject.data.database.GenreDB
import com.abramovae.newproject.data.database.MovieDB
import com.abramovae.newproject.data.database.Repository
import com.android.academy.fundamentals.homework.features.data.Genre
import com.android.academy.fundamentals.homework.features.data.Movie
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MyWorker(context: Context, workerParams: WorkerParameters): Worker(context, workerParams) {

    private val loadMoviesApi = RetrofitModule.loadMoviesApi
    private val repo = Repository(context.applicationContext)
    lateinit var genresList: List<Genre>

    override fun doWork(): Result {
        return try {
            GlobalScope.launch {
                job()
            }
            Result.success()
        } catch (error: Throwable) {
            Result.failure()
            // Result.retry()
        }
    }

    private suspend fun job() {
        genresList = loadMoviesApi.loadGenres().genres
        var movies = loadMoviesApi.getMovies().movies.map {
            Movie(
                    it,
                    getGenres(it.genreIds),
                    kotlin.collections.emptyList<com.android.academy.fundamentals.homework.features.data.Actor>()
            )
        }
        var moviesdb: List<MovieDB> = convertMovies(movies)
        var genresDb: List<GenreDB> = convertGenres(genresList)
        repo.saveAllMovies(moviesdb)
        repo.saveAllGenres(genresDb)
//        var m = repo.getAllMovies().get(0);
//
//
//        val notification = NotificationCompat.Builder(applicationContext, "CHANNEL")
//                .setContentTitle(chat.contact.name)
//                .setContentText(message.text)
//                .setSmallIcon(R.drawable.ic_message)
//                .setWhen(message.timestamp)
//                .setLargeIcon(bitmapIcon)
//                .build()
//        val notificationManager = NotificationManagerCompat.from(context)
//        notificationManager.notify("chat", chat.id, notification)


    }

    fun getGenres(genreIds: List<Int>): List<Genre>{
        return genresList.filter { genreIds.contains(it.id) }
    }
    fun convertGenres(genres: List<Genre>): List<GenreDB>{
        return genres.map {
            GenreDB(
                    it.id, it.name
            )
        }
    }
    fun convertMovies(movies: List<Movie>) : List<MovieDB>{
        return movies.map {
            MovieDB(
                    it.id,
                    it.title,
                    it.overview,
                    it.poster,
                    it.backdrop,
                    it.ratings,
                    it.adult,
                    it.runtime,
                    it.genreIds,
            )
        }
    }
}
