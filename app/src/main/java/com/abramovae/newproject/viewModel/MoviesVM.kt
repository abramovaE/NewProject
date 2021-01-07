package com.abramovae.newproject.viewModel

import android.content.Context
import androidx.lifecycle.*
import com.abramovae.newproject.repo.IMoviesRepo
import com.abramovae.newproject.repo.MoviesRepo
import com.android.academy.fundamentals.homework.features.data.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.IllegalArgumentException

class MoviesVM(private val repo: IMoviesRepo): ViewModel(){


    private val _selectedMovie = MutableLiveData<Movie>()
    val selectedMovie get() = _selectedMovie


    private val _movies = MutableLiveData<List<Movie>>()
    val movies get() = _movies


    fun select(movie: Movie) {
        viewModelScope.launch {
            _selectedMovie.value = movie
        }
    }

    fun load(){
        lateinit var data: List<Movie>
        viewModelScope.launch{
            withContext(Dispatchers.IO) {
                data = repo.getMovies()
            }
            _movies.value = data
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(context: Context) :
        ViewModelProvider.NewInstanceFactory() {
        private val appContext = context.applicationContext

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            when (modelClass){
                MoviesVM::class.java -> MoviesVM(MoviesRepo(appContext)) as T
                else -> throw IllegalArgumentException()
            }
            return MoviesVM(MoviesRepo(appContext)) as T
        }
    }
}