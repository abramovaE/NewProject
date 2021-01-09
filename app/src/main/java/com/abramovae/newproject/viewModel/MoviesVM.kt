package com.abramovae.newproject.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.abramovae.newproject.data.RetrofitModule
import com.abramovae.newproject.repo.LoadMovieInterceptor
import com.abramovae.newproject.repo.LoadMoviesInt
import com.android.academy.fundamentals.homework.features.data.Actor
import com.android.academy.fundamentals.homework.features.data.Genre
import com.android.academy.fundamentals.homework.features.data.Movie
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import java.lang.IllegalArgumentException
import java.util.concurrent.TimeUnit

class MoviesVM(val loadMoviesApi: LoadMoviesInt): ViewModel() {

    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }


    private val _selectedMovie = MutableLiveData<Movie>()
    val selectedMovie get() = _selectedMovie


    private val _movies = MutableLiveData<List<Movie>>()
    val movies get() = _movies


    private val _genres = MutableLiveData<List<Genre>>()
    val genres get() = _genres

    lateinit var genresList: List<Genre>

    fun select(movie: Movie) {
        viewModelScope.launch {
            _selectedMovie.value = movie
        }
    }

    fun load() {
        lateinit var movies: List<Movie>
        viewModelScope.launch(handler) {
            withContext(Dispatchers.IO) {
                genresList = loadMoviesApi.loadGenres().genres
                movies = loadMoviesApi.getMovies().movies.map {
                    Movie(
                        it.id,
                        it.title,
                        it.overview,
                        it.poster,
                        it.backdrop,
                        it.ratings,
                        it.adult,
                        it.runtime,
                        it.genreIds,

                        getGenres(it.genreIds),
                        getActors(it.id)

                    )
                }
            }
            _movies.value = movies
        }
    }

    fun getGenres(genreIds: List<Int>): List<Genre>{
        return genresList.filter { genreIds.contains(it.id) }
    }

    suspend fun getActors(movieId: Int): List<Actor>{
        return loadMoviesApi.loadMovieActors(movieId).actors
    }


    fun setMovies(m: List<Movie>) {
        _movies.value = m
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(context: Context) :
        ViewModelProvider.NewInstanceFactory() {
        private val appContext = context.applicationContext

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            when (modelClass) {
                MoviesVM::class.java -> MoviesVM(RetrofitModule.RetrofitModule.loadMoviesApi) as T
                else -> throw IllegalArgumentException()
            }
            return MoviesVM(RetrofitModule.RetrofitModule.loadMoviesApi) as T
        }
    }




}