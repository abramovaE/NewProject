package com.abramovae.newproject.repo

import com.abramovae.newproject.data.GenresRespClass
import com.abramovae.newproject.data.MovieCreditRespClass
import com.abramovae.newproject.data.MovieRespClass
import com.android.academy.fundamentals.homework.features.data.Actor
import com.android.academy.fundamentals.homework.features.data.Genre
import com.android.academy.fundamentals.homework.features.data.Movie
import com.android.academy.fundamentals.homework.features.data.MovieTest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import java.net.URL

interface LoadMoviesInt {



    @GET("3/movie/popular?api_key=1bbcd34e71c300a0267ad1411ec2bc84&language=ru-Ru&page=1")
    suspend fun getMovies(): MovieRespClass

    @GET("3/genre/movie/list?api_key=1bbcd34e71c300a0267ad1411ec2bc84&language=ru-Ru")
    suspend fun loadGenres(): GenresRespClass

    @GET("3/movie/{movie_id}/credits?api_key=1bbcd34e71c300a0267ad1411ec2bc84&language=ru-Ru")
    suspend fun loadMovieActors(@Path("movie_id") movieId: Int): MovieCreditRespClass



//    override fun onResponse(
//        call: Call<List<MovieTest>>,
//        response: Response<List<MovieTest>>
//    ) {
//        val movies: List<MovieTest> = response.body() ?: emptyList()
//    }




}