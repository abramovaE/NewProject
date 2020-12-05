package com.abramovae.newproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ActorsAdapter(var actors: List<Actor>): RecyclerView.Adapter<ActorsViewHolder>() {




    override fun getItemCount(): Int {
        return actors.size
    }

    fun getItem(position: Int): Actor{
        return actors[position]

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)


        return ActorsViewHolder(inflater.inflate(R.layout.view_holder_actor, parent, false))
    }

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}