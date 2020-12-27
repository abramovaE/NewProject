package com.abramovae.newproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.academy.fundamentals.homework.features.data.Actor
import com.android.academy.fundamentals.homework.features.data.Movie

class ActorsAdapter(movie: Movie): RecyclerView.Adapter<ActorsViewHolder>() {


    var actors: List<Actor> = movie.actors

    override fun getItemCount(): Int {
        if(actors != null) {
            return actors.size
        }
        return 0;
    }

    fun getItem(position: Int): Actor {
        return actors.get(position)!!

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.view_holder_actor, parent, false)
        view.getLayoutParams().width = parent.width/4;
        return ActorsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}