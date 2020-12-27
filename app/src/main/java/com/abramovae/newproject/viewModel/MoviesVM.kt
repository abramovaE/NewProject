package com.abramovae.newproject.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.academy.fundamentals.homework.features.data.Movie

class MoviesVM(val movies: ArrayList<Movie>): ViewModel(){

    private val _mutableMoviesList = MutableLiveData<List<Movie>>()
    val moviesList: LiveData<List<Movie>> get() = _mutableMoviesList

    @Suppress("UNCHECKED_CAST")
    class Factory(private val movies: ArrayList<Movie>) :
        ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MoviesVM(movies) as T
        }
    }
}