package com.abramovae.newproject.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abramovae.newproject.*
import com.abramovae.newproject.viewModel.MoviesVM
import com.android.academy.fundamentals.homework.features.data.Movie


class FragmentMoviesList: Fragment()
     {

    val FRAGMENT_MOVIE_DETAILS_TAG = "FRAGMENT_MOVIE_DETAILS"
    private lateinit var viewModel: MoviesVM



    companion object {
        fun newInstance(movies: List<Movie>): FragmentMoviesList {
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
        var view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        var list = view.findViewById<RecyclerView>(R.id.rvMovies)
        var movies = arguments?.getParcelableArrayList<Movie>("movies")

        viewModel = ViewModelProvider((activity as MainActivity), MoviesVM.Factory()).get(MoviesVM::class.java)

        list.layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
        list.adapter = movies?.let {
            MoviesAdapter(it, viewModel)
        };

        return view;
    }

         override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
             super.onViewCreated(view, savedInstanceState)

             viewModel.selectedMovie.observe(this.viewLifecycleOwner, this::selected)
         }


    fun toFragmentMovieDetails(movie: Movie){
        var fm = this.fragmentManager
        var fragmentMovieDetails =
            FragmentMovieDetails.newInstance(
            )
        fragmentMovieDetails?.apply {
            fm
            ?.beginTransaction()
                    ?.add(R.id.frame, this, FRAGMENT_MOVIE_DETAILS_TAG)
                    ?.addToBackStack(FRAGMENT_MOVIE_DETAILS_TAG)
                    ?.commit()
        }
    }


    fun selected(movie: Movie){
        toFragmentMovieDetails(movie)
    }
}


