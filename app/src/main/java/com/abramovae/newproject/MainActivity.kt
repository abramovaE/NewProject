package com.abramovae.newproject

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.abramovae.newproject.view.FragmentMovieDetails
import com.abramovae.newproject.view.FragmentMoviesList
import com.abramovae.newproject.viewModel.MoviesVM
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

    lateinit var viewModel: MoviesVM


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this,
            MoviesVM.Factory()
        ).get(MoviesVM::class.java)

        scope.async{
            var job = async{
                readFromFile()
            }.await()
        }

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

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }


    private suspend fun readFromFile() = withContext(Dispatchers.IO) {
        movies = loadMovies(this@MainActivity)
        viewModel.setMovies(movies)
    }
}