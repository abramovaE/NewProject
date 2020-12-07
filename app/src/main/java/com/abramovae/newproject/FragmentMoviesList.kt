package com.abramovae.newproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class FragmentMoviesList: Fragment(), ClickListener{

    val FRAGMENT_MOVIE_DETAILS_TAG = "FRAGMENT_MOVIE_DETAILS"



    companion object {
        fun newInstance(): FragmentMoviesList {
            val args = Bundle()
            val fragment = FragmentMoviesList()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        var list = view.findViewById<RecyclerView>(R.id.rvMovies)

        list.layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
        list.adapter = MoviesAdapter(this);
        return view;
    }




    fun toFragmentMovieDetails(movie: Movie){
        var fm = activity!!.supportFragmentManager
        var fragmentMovieDetails = FragmentMovieDetails.newInstance(movie)
        fragmentMovieDetails?.apply {
            fm
            ?.beginTransaction()
                    ?.add(R.id.frame, this, FRAGMENT_MOVIE_DETAILS_TAG)
                    ?.addToBackStack(FRAGMENT_MOVIE_DETAILS_TAG)
                    ?.commit()
        }
    }


    override fun onClick(movie: Movie) {
        toFragmentMovieDetails(movie)
    }
}