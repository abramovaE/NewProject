package com.abramovae.newproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.academy.fundamentals.homework.features.data.Actor
import com.android.academy.fundamentals.homework.features.data.Movie

class ActorsAdapter(movie: Movie): RecyclerView.Adapter<ActorsViewHolder>() {

    var a1: ActorM  = ActorM("actor one", R.drawable.movie0)
    var a2: ActorM = ActorM("actor two",  R.drawable.movie1)
    var a3: ActorM  = ActorM("actor three",  R.drawable.movie2)
    var a4: ActorM = ActorM("actor four",  R.drawable.movie3)
    var a5: ActorM  = ActorM("actor five",  R.drawable.movie1)
    var a6: ActorM = ActorM("actor six",  R.drawable.movie5)

    var actors: List<Actor>? = movie.actors


    private fun getActors(movie: Movie): Int {
//        this.actors = movie.actors
        return actors!!.size;
//        val name = movie.title
//        if(name == "Avengers: End Game"){
//            return listOf(a1, a2, a3, a4, a6);
//        }
//        else if(name == "Tenet"){
//            return listOf(a3, a2, a1, a4, a5)
//        }
//        else if(name == "Black Widow"){
//            return listOf(a1, a2, a3, a4, a5)
//        }
//
//        else {
//            return listOf(a4, a2, a3, a4, a5, a6)
//        }
    }


    override fun getItemCount(): Int {
        if(actors != null) {
            return actors!!.size
        }
        return 0;
    }

    fun getItem(position: Int): Actor {
        return actors?.get(position)!!

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