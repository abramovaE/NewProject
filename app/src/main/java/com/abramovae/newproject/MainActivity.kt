package com.abramovae.newproject

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.work.WorkManager
import com.abramovae.newproject.data.service.WorkerRepo
import com.abramovae.newproject.view.FragmentMovieDetails
import com.abramovae.newproject.view.FragmentMoviesList
import com.abramovae.newproject.viewModel.MoviesVM
import com.android.academy.fundamentals.homework.features.data.Movie


class MainActivity : AppCompatActivity(){

    private val workRepository = WorkerRepo();

    val FRAGMENT_MOVIE_DETAILS_TAG = "FRAGMENT_MOVIE_DETAILS"
    val FRAGMENT_MOVIES_LIST_TAG = "FRAGMENT_MOVIES_LIST"

    private var fragmentMovieDetails: FragmentMovieDetails? = null
    private var fragmentMoviesList: FragmentMoviesList? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            fragmentMoviesList = FragmentMoviesList.newInstance()
            fragmentMoviesList?.apply {
                supportFragmentManager.beginTransaction()
                    .add(R.id.frame, this, FRAGMENT_MOVIES_LIST_TAG)
                    .commit()
            }
            intent?.let(::handleIntent)

        } else {
            fragmentMovieDetails =
                supportFragmentManager.findFragmentByTag(FRAGMENT_MOVIE_DETAILS_TAG) as? FragmentMovieDetails
            fragmentMoviesList =
                supportFragmentManager.findFragmentByTag(FRAGMENT_MOVIES_LIST_TAG) as? FragmentMoviesList
        }

        WorkManager.getInstance(this).enqueue(workRepository.locationWork)

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null) {
            handleIntent(intent)
        }
    }

    private fun handleIntent(intent: Intent) {
        when (intent.action) {
            // Invoked when a dynamic shortcut is clicked.
            Intent.ACTION_VIEW -> {
//                var id = databaseList().lastIndexOf("/") + 1
                var viewModel = ViewModelProvider((this), MoviesVM.Factory(this)).get(
                    MoviesVM::class.java)

                var movie = intent.getParcelableExtra<Movie>("movie")
                if (movie != null) {
                    viewModel.select(movie)
                }
            }
        }
    }
}