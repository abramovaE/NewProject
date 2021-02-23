package com.abramovae.newproject.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abramovae.newproject.MainActivity
import com.abramovae.newproject.R
import com.abramovae.newproject.viewModel.MoviesVM
import com.android.academy.fundamentals.homework.features.data.Movie
import com.google.android.material.transition.MaterialElevationScale

class FragmentMoviesList: Fragment(), ClickListener {

    val FRAGMENT_MOVIE_DETAILS_TAG = "FRAGMENT_MOVIE_DETAILS"
    private lateinit var viewModel: MoviesVM
    lateinit var adapter: MoviesAdapter
    private lateinit var _view: View
    private lateinit var list: RecyclerView
    companion object {
        fun newInstance(): FragmentMoviesList {
            return FragmentMoviesList()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exitTransition = MaterialElevationScale(false)
        reenterTransition = MaterialElevationScale(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        list = view.findViewById<RecyclerView>(R.id.rvMovies)
        viewModel = ViewModelProvider((activity as MainActivity), MoviesVM.Factory(activity as MainActivity)).get(MoviesVM::class.java)
        viewModel.load()
        list.layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
        adapter = MoviesAdapter(this)
        list.adapter = adapter
        _view = view;
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.selectedMovie.observe(this.viewLifecycleOwner, this::selected)
        viewModel.movies.observe(this.viewLifecycleOwner, this::updateAdapter)
        viewModel.exText.observe(this.viewLifecycleOwner, this::showErrorToast)
    }

    fun toFragmentMovieDetails(movie: Movie){
        var fm = this.fragmentManager
        var fragmentMovieDetails = FragmentMovieDetails.newInstance()
        var item = list?.findViewHolderForAdapterPosition(adapter.getPosition(movie))?.itemView
        fm?.beginTransaction()
            ?.addSharedElement(item!!, getString(R.string.movie_transition))
            ?.setReorderingAllowed(true)
            ?.add(R.id.frame, fragmentMovieDetails, FRAGMENT_MOVIE_DETAILS_TAG)
            ?.addToBackStack(FRAGMENT_MOVIE_DETAILS_TAG)
            ?.commit()
        postponeEnterTransition()
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
    }

    fun showErrorToast(ext: String) {
        Toast.makeText(activity, ext, Toast.LENGTH_SHORT).show()
    }
}


