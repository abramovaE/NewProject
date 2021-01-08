package com.abramovae.newproject.data

import com.android.academy.fundamentals.homework.features.data.Actor
import com.android.academy.fundamentals.homework.features.data.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieCreditRespClass(


    @SerialName("id")
    val id: Int,

    @SerialName("cast")
    val actors: List<Actor>


    )



{

}