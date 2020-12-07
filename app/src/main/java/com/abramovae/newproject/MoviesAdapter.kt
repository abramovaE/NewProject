package com.abramovae.newproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList


class MoviesAdapter(private val clickListener: ClickListener): RecyclerView.Adapter<MoviesViewHolder>() {


    var m1: Movie  = Movie("Avengers: End Game", "Action, Adventure, Drama", 137, 125, R.drawable.avengers, 13, 4)
    var m2: Movie  = Movie("Tenet", "Action, Sci-Fi, Thriller", 97, 98, R.drawable.tenet, 16, 5)
    var m3: Movie  = Movie("Black Widow", "Action, Adventure, Sci-Fi", 102, 38, R.drawable.widow, 13, 4)
    var m4: Movie  = Movie("Wonder Woman 1984", "Action, Adventure, Fantasy", 120, 74, R.drawable.ww84, 13, 5)

    var m12: Movie  = Movie("Avengers: End Game", "Action, Adventure, Drama", 137, 125, R.drawable.avengers, 13, 4)
    var m27: Movie  = Movie("Tenet", "Action, Sci-Fi, Thriller", 97, 98, R.drawable.tenet, 16, 5)
    var m38: Movie  = Movie("Black Widow", "Action, Adventure, Sci-Fi", 102, 38, R.drawable.widow, 13, 4)
    var m49: Movie  = Movie("Wonder Woman 1984", "Action, Adventure, Fantasy", 120, 74, R.drawable.ww84, 13, 5)

    private var movies: ArrayList<Movie> = arrayListOf(m1, m2, m3, m4, m12, m27, m38, m49)

    override fun getItemCount(): Int {
        return movies.size
    }

    fun getItem(position: Int): Movie{
        return movies[position]

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view =  MoviesViewHolder(inflater.inflate(R.layout.view_holder_movie, parent, false))
        return view
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.findViewById<View>(R.id.bg).setOnClickListener { clickListener.onClick(getItem(position)) }

    }
}


interface ClickListener{
    fun onClick(movie: Movie)
}