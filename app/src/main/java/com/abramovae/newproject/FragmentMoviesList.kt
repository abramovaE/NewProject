package com.abramovae.newproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class FragmentMoviesList: Fragment(){

    companion object {
        fun newInstance(movies: ArrayList<Movie>): FragmentMoviesList {
            val args = Bundle()
            args.putParcelableArrayList("movies", movies as ArrayList<Movie>)
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
        val arg: Bundle? = arguments
        val movies: ArrayList<Movie> =  arg?.getParcelableArrayList<Movie>("movies") as ArrayList<Movie>

        var view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        var list = view.findViewById<RecyclerView>(R.id.rvMovies)


        list.layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
        list.adapter = MoviesAdapter(movies);

        return view;
    }
}