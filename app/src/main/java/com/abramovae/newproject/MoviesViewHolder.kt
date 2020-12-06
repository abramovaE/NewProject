package com.abramovae.newproject

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MoviesViewHolder(view: View): RecyclerView.ViewHolder(view) {

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


    fun bind(movie: Movie){
        title.setText(movie.title)
        genre.setText(movie.genre)
        picture.setImageResource(movie.imgId)
        dur.setText("" + movie.dur + " min")
        reviews.setText("" + movie.reviews + "reviews")
        age.setText("" + movie.age + "+")

        var stars = listOf(star0, star1, star2, star3, star4);
        for( (index, element) in stars.withIndex()){
            element.setImageResource(R.drawable.star)
            if(index < movie.stars){
                element.setImageResource(R.drawable.star2)
            }
        }

    }
}

