package com.abramovae.newproject.viewModel

import androidx.lifecycle.*
import com.android.academy.fundamentals.homework.features.data.Movie
import kotlinx.coroutines.launch

class MoviesVM(): ViewModel(){

    private val _selectedMovie = MutableLiveData<Movie>()
    val selectedMovie get() = _selectedMovie

    private val _movies = MutableLiveData<List<Movie>>()
    val movies get() = _movies


    fun select(movie: Movie) {
        viewModelScope.launch {
            _selectedMovie.value = movie
        }
    }

    fun setMovies(movies: List<Movie>){
        viewModelScope.launch {
            _movies.value = movies
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory() :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MoviesVM() as T
        }
    }
}