package com.abramovae.newproject.data

import com.android.academy.fundamentals.homework.features.data.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//@Serializable
data class MovieRespClass(


    @SerialName("page")
    val page: Int,

    @SerialName("results")
    val movies: List<Movie>


    )



{

}