package com.abramovae.newproject

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.ArrayList


class MainActivity : AppCompatActivity(){


    var m1: Movie  = Movie("Avengers: End Game", "Action, Adventure, Drama", 137, 125, R.drawable.avengers, 13, 4)
    var m2: Movie  = Movie("Tenet", "Action, Sci-Fi, Thriller", 97, 98, R.drawable.tenet, 16, 5)
    var m3: Movie  = Movie("Black Widow", "Action, Adventure, Sci-Fi", 102, 38, R.drawable.widow, 13, 4)
    var m4: Movie  = Movie("Wonder Woman 1984", "Action, Adventure, Fantasy", 120, 74, R.drawable.ww84, 13, 5)

    var movies: ArrayList<Movie> = arrayListOf(m1, m2, m3, m4)

    val FRAGMENT_MOVIE_DETAILS_TAG = "FRAGMENT_MOVIE_DETAILS"
    val FRAGMENT_MOVIES_LIST_TAG = "FRAGMENT_MOVIES_LIST"

    private var fragmentMovieDetails: FragmentMovieDetails? = null
    private var fragmentMoviesList: FragmentMoviesList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            fragmentMoviesList = FragmentMoviesList.newInstance(movies)
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



    fun backClick(v: View){
        supportFragmentManager.popBackStack()
    }

    fun getMovieByName(name: String): Movie {
        if(name == "Avengers: End Game"){
            return m1;
        }
        else if(name == "Tenet"){
            return m2
        }
        else if(name == "Black Widow"){
            return m3
        }

        else {
            return m4
        }
    }

    fun movieDetailsClick(v: View){
        val imgView = v.parent as ConstraintLayout
        val textView = imgView.findViewById<TextView>(R.id.name)
        Log.d("log", textView.text as String)
        fragmentMovieDetails = FragmentMovieDetails.newInstance(getMovieByName(textView.text as String))
        fragmentMovieDetails?.apply {
            supportFragmentManager.beginTransaction()
                    .add(R.id.frame, this, FRAGMENT_MOVIE_DETAILS_TAG)
                    .addToBackStack(FRAGMENT_MOVIE_DETAILS_TAG)
                    .commit()
        }
    }
}