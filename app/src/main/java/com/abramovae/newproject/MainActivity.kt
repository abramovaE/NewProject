package com.abramovae.newproject

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout


class MainActivity : AppCompatActivity(){

    val FRAGMENT_MOVIE_DETAILS_TAG = "FRAGMENT_MOVIE_DETAILS"
    val FRAGMENT_MOVIES_LIST_TAG = "FRAGMENT_MOVIES_LIST"

    private var fragmentMovieDetails: FragmentMovieDetails? = null
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
                supportFragmentManager.findFragmentByTag(FRAGMENT_MOVIE_DETAILS_TAG) as? FragmentMovieDetails
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
        val imgView = v.parent as ConstraintLayout
        val textView = imgView.findViewById<TextView>(R.id.name)
        Log.d("log", textView.text as String)
        fragmentMovieDetails = FragmentMovieDetails.newInstance((textView.text as String))
        fragmentMovieDetails?.apply {
            supportFragmentManager.beginTransaction()
                    .add(R.id.frame, this, FRAGMENT_MOVIE_DETAILS_TAG)
                    .addToBackStack(FRAGMENT_MOVIE_DETAILS_TAG)
                    .commit()
        }
    }


}