package com.abramovae.newproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView



//5. Create Adapter for RecyclerView to get data and assign it to ViewHolder
//6. Create view_holder_actor.xml
//7. RecyclerView in fragment_movies_list should show movies list in two columns
//8. Create view_holder_actor.xml
//9. Move your common views for actor view from fragment_movies_details.xml to view_holder_actor.xml
//10. Add RecyclerView into fragment_movies_details.xml
//11. Create ViewHolder for RecyclerView that will show view_holder_actor.xml
//12. Create Adapter for RecyclerView to get data and assign it to ViewHolder
//13. RecyclerView in fragment_movies_details should show actors list in a row


class MoviesAdapter(var movies: List<Movie>): RecyclerView.Adapter<MoviesViewHolder>() {

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
    }



}