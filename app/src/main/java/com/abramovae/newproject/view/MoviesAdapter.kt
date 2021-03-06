package com.abramovae.newproject.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abramovae.newproject.R
import com.android.academy.fundamentals.homework.features.data.Movie
import java.util.ArrayList


class MoviesAdapter(private val clickListener: ClickListener): RecyclerView.Adapter<MoviesViewHolder>() {

    private var movies = ArrayList<Movie>()

    fun setNewMovies(movies: List<Movie>){
        this.movies = movies as ArrayList<Movie>
    }


    override fun getItemCount(): Int {
        return movies.size
    }

    fun getItem(position: Int): Movie{
        return movies[position]

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = MoviesViewHolder(
            inflater.inflate(
                R.layout.view_holder_movie,
                parent,
                false
            )
        )

        return view
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.findViewById<View>(R.id.bg).setOnClickListener {
            clickListener.onClick(getItem(position))
        }

    }


}


interface ClickListener{
    fun onClick(movie: Movie)
}