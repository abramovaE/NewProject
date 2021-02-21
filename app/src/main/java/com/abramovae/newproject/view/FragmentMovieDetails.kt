package com.abramovae.newproject.view

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Explode
import androidx.transition.TransitionInflater
import com.abramovae.newproject.MainActivity
import com.abramovae.newproject.R
import com.abramovae.newproject.viewModel.MoviesVM
import com.android.academy.fundamentals.homework.features.data.Movie
import com.bumptech.glide.Glide
import com.google.android.material.transition.MaterialContainerTransform
import java.util.concurrent.TimeUnit
import kotlin.collections.listOf as listOf1


class FragmentMovieDetails: Fragment(), View.OnClickListener{
    private lateinit var viewModel: MoviesVM
    private lateinit var backGround: ImageView
    private lateinit var movieName: TextView
    private lateinit var age: TextView
    private lateinit var genre:TextView
    private lateinit var reviews: TextView
    private lateinit var backBtn: TextView
    private lateinit var overView: TextView
    private lateinit var stars: List<ImageView>
    private lateinit var list: RecyclerView
    private lateinit var addToCalendarBtn: TextView
    val BASE_URL = "https://image.tmdb.org/t/p/w500"

    val FRAGMENT_CALENDAR_TAG = "FRAGMENT_CALENDAR"


    companion object {
        fun newInstance(): FragmentMovieDetails {
            val args = Bundle()
            val fragment = FragmentMovieDetails()
            fragment.arguments = args

            return fragment
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider((activity as MainActivity), MoviesVM.Factory(activity as MainActivity)).get(MoviesVM::class.java)
        viewModel.selectedMovie.observe(this.viewLifecycleOwner, this::setMovieDetails)
        viewModel.exText.observe(this.viewLifecycleOwner, this::showErrorToast)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View{



        var view = inflater.inflate(R.layout.fragment_movies_details, container, false)
        backGround = view.findViewById<ImageView>(R.id.imageView)
        movieName = view.findViewById<TextView>(R.id.movieName)
        age = view.findViewById(R.id.age)
        genre = view.findViewById(R.id.movieGenre)
        reviews = view.findViewById(R.id.movieReviews)
        backBtn = view.findViewById(R.id.backBtn)
        overView = view.findViewById(R.id.overview)
        backBtn.setOnClickListener(this)
        addToCalendarBtn = view.findViewById(R.id.addToCalendar)
        addToCalendarBtn.setOnClickListener(this)

        val star0: ImageView = view.findViewById(R.id.star0)
        val star1: ImageView = view.findViewById(R.id.star1)
        val star2: ImageView = view.findViewById(R.id.star2)
        val star3: ImageView = view.findViewById(R.id.star3)
        val star4: ImageView = view.findViewById(R.id.star4)
        stars = listOf1(star0, star1, star2, star3, star4);

        list = view.findViewById<RecyclerView>(R.id.rvActors)
        list.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        sharedElementEnterTransition = MaterialContainerTransform().addTarget(view!!)
        postponeEnterTransition(1000L, TimeUnit.MILLISECONDS)



        return view
    }

    override fun onClick(v: View) {
        if(v.id == R.id.backBtn){
            this.fragmentManager?.popBackStack()
        }
        else if(v.id == R.id.addToCalendar){
            toFragmentCalendar()
        }
    }

    private fun setMovieDetails(movie: Movie){

            Glide.with(this).load(
                Uri.parse(BASE_URL + movie.backdrop)
            ).into(backGround)

        movieName.text = movie.title
        genre.text = movie.genres.toString().removePrefix("[").removeSuffix("]")
        reviews.text = getString(R.string.reviews)
        overView.setText(movie.overview)
        reviews.setText(getString(R.string.reviews))
        if(movie.adult){
            age.setText(getString(R.string.age16));
        } else{
            age.setText(getString(R.string.age13));
        }

        for( (index, element) in stars.withIndex()){
            element.setImageResource(R.drawable.star)
            if(index < movie.getRating()){
                element.setImageResource(R.drawable.star2)
            }
        }
        list.adapter = ActorsAdapter(movie)
    }

    fun showErrorToast(ext: String) {
       Toast.makeText(activity, ext, Toast.LENGTH_SHORT).show()
    }

    fun toFragmentCalendar(){
        var fm = this.fragmentManager
        var fragmentCalendar =
            FragmentCalendar.newInstance(
            )
        fragmentCalendar?.apply {
            fm
                ?.beginTransaction()
                ?.add(R.id.frame, this, FRAGMENT_CALENDAR_TAG)
                ?.addToBackStack(FRAGMENT_CALENDAR_TAG)
                ?.commit()
        }
    }
}