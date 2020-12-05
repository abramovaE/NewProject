package com.abramovae.newproject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.listOf as listOf1

class FragmentMovieDetails: Fragment(){

    var m1: Movie  = Movie("Avengers: End Game", "Action, Adventure, Drama", 137, 125, R.drawable.avengers, 13, 4)
    var m2: Movie  = Movie("Tenet", "Action, Sci-Fi, Thriller", 97, 98, R.drawable.tenet, 16, 5)
    var m3: Movie  = Movie("Black Widow", "Action, Adventure, Sci-Fi", 102, 38, R.drawable.widow, 13, 4)
    var m4: Movie  = Movie("Wonder Woman 1984", "Action, Adventure, Fantasy", 120, 74, R.drawable.ww84, 13, 5)

    var movies= listOf1(m1, m2, m3, m4)



    companion object {
        fun newInstance(movieName: String): FragmentMovieDetails {
            val args = Bundle()
            args.putString("name", movieName)
            val fragment = FragmentMovieDetails()
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View{

        val arg: Bundle? = arguments
        val name = arg?.get("name");
        val movie: Movie;
        if(name == "Avengers: End Game"){
            movie = m1;
        }
        else if(name == "Tenet"){
            movie  = m2
        }
        else if(name == "Black Widow"){
            movie = m3
        }

        else {
            movie = m4
        }
        var view = inflater.inflate(R.layout.fragment_movies_details, container, false)

        val backGround = view?.findViewById<ImageView>(R.id.imageView)
        val movieName = view?.findViewById<TextView>(R.id.movieName)
        val age:TextView = view.findViewById(R.id.age)
        val genre:TextView = view.findViewById(R.id.movieGenre)
        val reviews: TextView = view.findViewById(R.id.movieReviews)


        backGround?.setImageResource(movie.imgId);
        movieName?.text = movie.title
        age?.text = "" + movie.age + "+"
        genre?.text = movie.genre
        reviews?.text = "" + movie.reviews + " reviews"

        val star0: ImageView = view.findViewById(R.id.star0)
        val star1: ImageView = view.findViewById(R.id.star1)
        val star2: ImageView = view.findViewById(R.id.star2)
        val star3: ImageView = view.findViewById(R.id.star3)
        val star4: ImageView = view.findViewById(R.id.star4)
        var stars = listOf1(star0, star1, star2, star3, star4);
        for( (index, element) in stars.withIndex()){
            element.setImageResource(R.drawable.star)
            if(index < movie.stars){
                element.setImageResource(R.drawable.star2)
            }
        }

        Log.d("LOG", movie.title)



        var list = view.findViewById<RecyclerView>(R.id.rvActors)

        var a1: Actor  = Actor("actor one", R.drawable.movie0)
        var a2: Actor = Actor("actor two",  R.drawable.movie1)
        var a3: Actor  = Actor("actor three",  R.drawable.movie2)
        var a4: Actor = Actor("actor four",  R.drawable.movie3)
        var a5: Actor  = Actor("actor five",  R.drawable.movie1)
        var a6: Actor = Actor("actor six",  R.drawable.movie5)

        var actors = listOf1(a1, a2, a3, a4, a5)


        var l = GridLayoutManager(activity, 1, GridLayoutManager.HORIZONTAL, false)

//        var l = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        list.layoutManager = l
//        list.setHasFixedSize(true)
//        list.setHasFixedSize(true)



        list.adapter = ActorsAdapter(actors)
        return view
    }

}