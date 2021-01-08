package com.abramovae.newproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abramovae.newproject.MainActivity
import com.abramovae.newproject.R
import com.abramovae.newproject.viewModel.MoviesVM
import com.android.academy.fundamentals.homework.features.data.Movie
import java.util.ArrayList


class FragmentMoviesList: Fragment(), ClickListener
     {

    val FRAGMENT_MOVIE_DETAILS_TAG = "FRAGMENT_MOVIE_DETAILS"
    private lateinit var viewModel: MoviesVM

         lateinit var adapter: MoviesAdapter


    companion object {
        fun newInstance(): FragmentMoviesList {
            return FragmentMoviesList()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        var list = view.findViewById<RecyclerView>(R.id.rvMovies)

        viewModel = ViewModelProvider((activity as MainActivity), MoviesVM.Factory(activity as MainActivity)).get(MoviesVM::class.java)
        viewModel.load()


        list.layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
        adapter = MoviesAdapter(this)
        list.adapter = adapter


        return view;
    }

         override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
             super.onViewCreated(view, savedInstanceState)
             viewModel.selectedMovie.observe(this.viewLifecycleOwner, this::selected)
             viewModel.movies.observe(this.viewLifecycleOwner, this::updateAdapter)
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
         private fun updateAdapter(movies: List<Movie>) {
             adapter.setNewMovies(movies)
             adapter.notifyDataSetChanged()
         }

         override fun onClick(movie: Movie) {
             viewModel.select(movie)
//             toFragmentMovieDetails(movie)
         }
     }


