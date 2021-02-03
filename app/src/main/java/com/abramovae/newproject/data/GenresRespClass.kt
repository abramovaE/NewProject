package com.abramovae.newproject.data

import com.android.academy.fundamentals.homework.features.data.Genre
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenresRespClass(
    @SerialName("genres")
    val genres: List<Genre>){
}
