package com.abramovae.newproject.repo

import com.android.academy.fundamentals.homework.features.data.MovieTest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap
import java.net.URL

interface LoadMoviesInt {



    @GET("3/movie/popular?api_key=1bbcd34e71c300a0267ad1411ec2bc84&language=ru-Ru&page=1")
    suspend fun getMovies(): List<MovieTest>


//    override fun onResponse(
//        call: Call<List<MovieTest>>,
//        response: Response<List<MovieTest>>
//    ) {
//        val movies: List<MovieTest> = response.body() ?: emptyList()
//    }




}