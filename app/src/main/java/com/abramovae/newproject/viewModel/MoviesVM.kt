package com.abramovae.newproject.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.abramovae.newproject.data.RetrofitModule
import com.abramovae.newproject.repo.LoadMoviesInt
import com.abramovae.newproject.repo.Repository
import com.android.academy.fundamentals.homework.features.data.Actor
import com.android.academy.fundamentals.homework.features.data.Genre
import com.android.academy.fundamentals.homework.features.data.Movie
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException

class MoviesVM(private val loadMoviesApi: LoadMoviesInt,  private val repository: Repository): ViewModel() {

    private val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
        _exText.value = "the loading was failed"
    }

    private val _exText = MutableLiveData<String>()
    val exText get() = _exText


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
            withContext(Dispatchers.IO){
                genresList = repository.getAllGenres()
                movies = repository.getAllMovies()
                _movies.value = movies
            }



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
                        getActors(it.id!!)

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
                MoviesVM::class.java -> MoviesVM(RetrofitModule.loadMoviesApi, Repository(appContext)
                ) as T
                else -> throw IllegalArgumentException()
            }
            return MoviesVM(RetrofitModule.loadMoviesApi, Repository(appContext)) as T
        }
    }






}