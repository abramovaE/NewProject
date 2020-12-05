package com.abramovae.newproject

import android.icu.lang.UCharacter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentMoviesList: Fragment(), View.OnClickListener{

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        var list = view.findViewById<RecyclerView>(R.id.rvMovies)

        var m1: Movie  = Movie("Avengers: End Game", "Action, Adventure, Drama", 137, 125, R.drawable.avengers, 13, 4)
        var m2: Movie  = Movie("Tenet", "Action, Sci-Fi, Thriller", 97, 98, R.drawable.tenet, 16, 5)
        var m3: Movie  = Movie("Black Widow", "Action, Adventure, Sci-Fi", 102, 38, R.drawable.widow, 13, 4)
        var m4: Movie  = Movie("Wonder Woman 1984", "Action, Adventure, Fantasy", 120, 74, R.drawable.ww84, 13, 5)

        var movies= listOf(m1, m2, m3, m4)
        list.layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
        list.adapter = MoviesAdapter(movies);

        return view;
    }



    override fun onClick(v: View?) { val fragmentMovieDetails = FragmentMovieDetails.newInstance(("name"))
        fragmentMovieDetails?.apply {
            fragmentManager?.beginTransaction()
                    ?.add(R.id.frame, this, "FRAGMENT_MOVIE_DETAILS")
                    ?.addToBackStack("FRAGMENT_MOVIE_DETAILS")
                    ?.commit()
        }
    }
}