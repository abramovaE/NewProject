package com.abramovae.newproject

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View


class MainActivity : AppCompatActivity(){

    val FRAGMENT_MOVIE_DETAILS_TAG = "FRAGMENT_MOVIE_DETAILS"
    val FRAGMENT_MOVIES_LIST_TAG = "FRAGMENT_MOVIES_LIST"

    private var fragmentMovieDetails: FragmentMoviesDetails? = null
    private var fragmentMoviesList: FragmentMoviesList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            fragmentMoviesList = FragmentMoviesList()
            fragmentMoviesList?.apply {
                supportFragmentManager.beginTransaction()
                    .add(R.id.frame, this, FRAGMENT_MOVIES_LIST_TAG)
                    .commit()
            }
        }
        else{
            fragmentMovieDetails =
                supportFragmentManager.findFragmentByTag(FRAGMENT_MOVIE_DETAILS_TAG) as? FragmentMoviesDetails
            fragmentMoviesList =
                supportFragmentManager.findFragmentByTag(FRAGMENT_MOVIES_LIST_TAG) as? FragmentMoviesList
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }



    public fun backClick(v: View){
        supportFragmentManager.popBackStack()
    }

    public fun movieDetailsClick(v: View){
        fragmentMovieDetails = FragmentMoviesDetails()
        fragmentMovieDetails?.apply {
            supportFragmentManager.beginTransaction()
                    .add(R.id.frame, this, FRAGMENT_MOVIE_DETAILS_TAG)
                    .addToBackStack(FRAGMENT_MOVIE_DETAILS_TAG)
                    .commit()
        }
    }


}