package com.abramovae.newproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ActorsAdapter(movie: Movie): RecyclerView.Adapter<ActorsViewHolder>() {

    var a1: Actor  = Actor("actor one", R.drawable.movie0)
    var a2: Actor = Actor("actor two",  R.drawable.movie1)
    var a3: Actor  = Actor("actor three",  R.drawable.movie2)
    var a4: Actor = Actor("actor four",  R.drawable.movie3)
    var a5: Actor  = Actor("actor five",  R.drawable.movie1)
    var a6: Actor = Actor("actor six",  R.drawable.movie5)

    var actors = getActors(movie)


    private fun getActors(movie: Movie): List<Actor>{
        val name = movie.title
        if(name == "Avengers: End Game"){
            return listOf(a1, a2, a3, a4, a6);
        }
        else if(name == "Tenet"){
            return listOf(a3, a2, a1, a4, a5)
        }
        else if(name == "Black Widow"){
            return listOf(a1, a2, a3, a4, a5)
        }

        else {
            return listOf(a4, a2, a3, a4, a5, a6)
        }
    }


    override fun getItemCount(): Int {
        return actors.size
    }

    fun getItem(position: Int): Actor{
        return actors[position]

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