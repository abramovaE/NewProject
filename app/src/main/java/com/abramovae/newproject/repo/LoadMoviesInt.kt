package com.abramovae.newproject.repo

import com.abramovae.newproject.data.*
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

    @GET("3/movie/popular?page=1")
    suspend fun getMovies(): MovieRespClass

    @GET("3/genre/movie/list")
    suspend fun loadGenres(): GenresRespClass

    @GET("3/movie/{movie_id}/credits")
    suspend fun loadMovieActors(@Path("movie_id") movieId: Int): MovieCreditRespClass


    @GET("3/configuration?api_key=1bbcd34e71c300a0267ad1411ec2bc84")
    suspend fun getConfiguration(): ImageResp

}