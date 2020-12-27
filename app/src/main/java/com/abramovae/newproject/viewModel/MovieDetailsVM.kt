package com.abramovae.newproject.viewModel

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.academy.fundamentals.homework.features.data.Actor
import com.android.academy.fundamentals.homework.features.data.Movie

class MovieDetailsVM(movie: Movie): ViewModel(){

    private val _movie = MutableLiveData<Movie>()
    val movie get() = _movie




    @Suppress("UNCHECKED_CAST")
    class Factory(private val movie: Movie) :
        ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MovieDetailsVM(movie) as T
        }
    }



}