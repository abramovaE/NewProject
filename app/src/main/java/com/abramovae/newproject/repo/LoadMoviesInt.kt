package com.abramovae.newproject.repo

import com.abramovae.newproject.data.*

import retrofit2.http.GET
import retrofit2.http.Path


interface LoadMoviesInt {

    @GET("3/movie/popular?page=1")
    suspend fun getMovies(): MovieRespClass

    @GET("3/genre/movie/list")
    suspend fun loadGenres(): GenresRespClass

    @GET("3/movie/{movie_id}/credits")
    suspend fun loadMovieActors(@Path("movie_id") movieId: Int): MovieCreditRespClass


    @GET("3/configuration")
    suspend fun getConfiguration(): ImageResp

}