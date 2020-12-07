package com.abramovae.newproject

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {


    private var fragmentMovieDetails: FragmentMoviesDetails? = null
    private var fragmentMoviesList: FragmentMoviesList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            fragmentMovieDetails = FragmentMoviesDetails()
            fragmentMovieDetails?.apply {
                supportFragmentManager.beginTransaction()
                    .add(R.id.frame, this, "FRAGMENT_MOVIE_DETAILS")
                    .addToBackStack("FRAGMENT_MOVIE_DETAILS")
                    .commit()
            }

            fragmentMoviesList = FragmentMoviesList()
            fragmentMoviesList?.apply {
                supportFragmentManager.beginTransaction()
                    .add(R.id.frame, this, "FRAGMENT_MOVIES_LIST")
                    .addToBackStack("FRAGMENT_MOVIES_LIST")
                    .commit()
            }
        }
        else{
            fragmentMovieDetails =
                supportFragmentManager.findFragmentByTag("FRAGMENT_MOVIE_DETAILS") as? FragmentMoviesDetails
            fragmentMoviesList =
                supportFragmentManager.findFragmentByTag("FRAGMENT_MOVIES_LIST") as? FragmentMoviesList
        }
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }
}