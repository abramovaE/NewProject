package com.abramovae.newproject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.listOf as listOf1

class FragmentMovieDetails: Fragment(), View.OnClickListener{



    companion object {
        val MOVIE_TAG: String = "movie"

        fun newInstance(movie: Movie): FragmentMovieDetails {
            val args = Bundle()
            args.putParcelable(MOVIE_TAG, movie)
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
        val movie: Movie = arg?.get(MOVIE_TAG) as Movie
        var view = inflater.inflate(R.layout.fragment_movies_details, container, false)

        val backGround = view.findViewById<ImageView>(R.id.imageView)
        val movieName = view.findViewById<TextView>(R.id.movieName)
        val age:TextView = view.findViewById(R.id.age)
        val genre:TextView = view.findViewById(R.id.movieGenre)
        val reviews: TextView = view.findViewById(R.id.movieReviews)
        val backBtn: TextView = view.findViewById(R.id.backBtn)

        backBtn.setOnClickListener(this)

        backGround.setImageResource(movie.imgId);
        movieName.text = movie.title
        age.text = "" + movie.age + "+"
        genre.text = movie.genre
        reviews.text = "" + movie.reviews + " reviews"

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


//        var l = GridLayoutManager(activity, 1, GridLayoutManager.HORIZONTAL, false)
        list.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        list.adapter = ActorsAdapter(movie)
        return view
    }

    override fun onClick(v: View) {
        if(v.id == R.id.backBtn){
            this.fragmentManager?.popBackStack()
//            var fm = activity!!.supportFragmentManager
//            fm.popBackStack()
        }
    }

}