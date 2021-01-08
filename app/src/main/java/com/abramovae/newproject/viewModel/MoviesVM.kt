package com.abramovae.newproject.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.abramovae.newproject.repo.IMoviesRepo
import com.abramovae.newproject.repo.LoadMoviesInt
import com.abramovae.newproject.repo.MoviesRepo
import com.android.academy.fundamentals.homework.features.data.Actor
import com.android.academy.fundamentals.homework.features.data.Genre
import com.android.academy.fundamentals.homework.features.data.Movie
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import java.lang.IllegalArgumentException
import java.util.concurrent.TimeUnit

class MoviesVM(private val repo: IMoviesRepo): ViewModel() {


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
//        lateinit var data: List<Movie>
//        viewModelScope.launch{
//            withContext(Dispatchers.IO) {
//                data = repo.getMovies()
//            }
//            _movies.value = data
//        }

        lateinit var movies: List<Movie>
        lateinit var actors: List<Actor>
        actors = ArrayList<Actor>()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                genresList = RetrofitModule.loadMoviesApi.loadGenres().genres
                movies = RetrofitModule.loadMoviesApi.getMovies().movies.map {
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

        return RetrofitModule.loadMoviesApi.loadMovieActors(movieId).actors
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
                MoviesVM::class.java -> MoviesVM(MoviesRepo(appContext)) as T
                else -> throw IllegalArgumentException()
            }
            return MoviesVM(MoviesRepo(appContext)) as T
        }
    }


    private object RetrofitModule {
        private val json = Json {
            ignoreUnknownKeys = true
            isLenient = true

        }
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        @Suppress("EXPERIMENTAL_API_USAGE")
        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(httpClient)
            .build()

        val loadMoviesApi: LoadMoviesInt = retrofit.create()
    }

}