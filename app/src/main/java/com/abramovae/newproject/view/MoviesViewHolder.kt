package com.abramovae.newproject.view

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abramovae.newproject.R
import com.android.academy.fundamentals.homework.features.data.Movie
import com.bumptech.glide.Glide

class MoviesViewHolder(view: View): RecyclerView.ViewHolder(view){



    private val title: TextView = itemView.findViewById(R.id.name)
    private val picture: ImageView = itemView.findViewById(R.id.bgg)
    private val genre: TextView = itemView.findViewById(R.id.genre)
    private val dur: TextView = itemView.findViewById(R.id.duration)
    private val reviews: TextView = itemView.findViewById(R.id.reviews)
    private val age: TextView = itemView.findViewById(R.id.age)
    private val star0: ImageView = itemView.findViewById(R.id.star_icon)
    private val star1: ImageView = itemView.findViewById(R.id.star_icon2)
    private val star2: ImageView = itemView.findViewById(R.id.star_icon3)
    private val star3: ImageView = itemView.findViewById(R.id.star_icon4)
    private val star4: ImageView = itemView.findViewById(R.id.star_icon5)

    var movie: Movie? = null


    fun bind(movie: Movie){
        this.movie = movie
        title.setText(movie.title)
        Glide.with(itemView.context).load(Uri.parse(movie.poster)).into(picture)
        dur.text = itemView.context.getString(R.string.min, movie.runtime)
        reviews.setText(itemView.context.getString(R.string.reviews))
        if(movie.adult){
            age.setText(itemView.context.getString(R.string.age16));
        } else{
            age.setText(itemView.context.getString(R.string.age13));
        }
        genre.setText(movie.genres.toString().removePrefix("[").removeSuffix("]"))
        var stars = listOf(star0, star1, star2, star3, star4);
        for( (index, element) in stars.withIndex()){
            element.setImageResource(R.drawable.star)
            if(index < movie.getRating()){
                element.setImageResource(R.drawable.star2)
            }
        }
    }
}