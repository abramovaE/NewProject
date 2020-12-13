package com.abramovae.newproject

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.academy.fundamentals.homework.features.data.Movie
import com.android.academy.fundamentals.homework.features.data.loadMovies
import kotlinx.coroutines.*
import java.util.ArrayList


class MainActivity : AppCompatActivity(){

    private val scope = CoroutineScope(Dispatchers.Main)
    private var movies: List<Movie> = ArrayList<Movie>()

    val FRAGMENT_MOVIE_DETAILS_TAG = "FRAGMENT_MOVIE_DETAILS"
    val FRAGMENT_MOVIES_LIST_TAG = "FRAGMENT_MOVIES_LIST"

    private var fragmentMovieDetails: FragmentMovieDetails? = null
    private var fragmentMoviesList: FragmentMoviesList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scope.async{
            var job = async{ readFromFile()}.await()
            if (savedInstanceState == null) {
                fragmentMoviesList = FragmentMoviesList.newInstance(movies)
                fragmentMoviesList?.apply {
                    supportFragmentManager.beginTransaction()
                        .add(R.id.frame, this, FRAGMENT_MOVIES_LIST_TAG)
                        .commit()
                }
            } else {
                fragmentMovieDetails =
                    supportFragmentManager.findFragmentByTag(FRAGMENT_MOVIE_DETAILS_TAG) as? FragmentMovieDetails
                fragmentMoviesList =
                    supportFragmentManager.findFragmentByTag(FRAGMENT_MOVIES_LIST_TAG) as? FragmentMoviesList
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }


    private suspend fun readFromFile() = withContext(Dispatchers.IO) {
        movies = loadMovies(this@MainActivity)


        Log.d("TAG 1", "movies: " + movies)

    }
}