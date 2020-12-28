package com.abramovae.newproject.viewModel

import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.*
import com.abramovae.newproject.R
import com.abramovae.newproject.view.FragmentMovieDetails
import com.android.academy.fundamentals.homework.features.data.Movie
import kotlinx.coroutines.launch

class MoviesVM(): ViewModel(){

    val FRAGMENT_MOVIE_DETAILS_TAG = "FRAGMENT_MOVIE_DETAILS"


//    private val _mutableMoviesList = MutableLiveData<List<Movie>>()
//    val moviesList: LiveData<List<Movie>> get() = _mutableMoviesList

    private val _selectedMovie = MutableLiveData<Movie>()
    val selectedMovie get() = _selectedMovie

    fun select(movie: Movie) {
        viewModelScope.launch {
            _selectedMovie.value = movie

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