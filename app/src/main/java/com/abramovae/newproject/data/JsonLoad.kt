package com.android.academy.fundamentals.homework.features.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.lifecycle.viewModelScope
import com.abramovae.newproject.MainActivity
import com.abramovae.newproject.viewModel.MoviesVM
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.net.URL


private val jsonFormat = Json { ignoreUnknownKeys = true }
var movies: List<MovieTest> = ArrayList<MovieTest>()
var genres: List<Genre> = ArrayList<Genre>()


//@Serializable
//private class JsonGenre(val id: Int, val name: String)

//@Serializable
//private class JsonActor(
//    val id: Int,
//    val name: String,
//    @SerialName("profile_path")
//    val profilePicture: String
//)

//@Serializable
//private class JsonMovie(
//    val id: Int,
//    val title: String,
//    @SerialName("poster_path")
//    val posterPicture: String,
//    @SerialName("backdrop_path")
//    val backdropPicture: String,
//    val runtime: Int,
//    @SerialName("genre_ids")
//    val genreIds: List<Int>,
//    val actors: List<Int>,
//    @SerialName("vote_average")
//    val ratings: Float,
//    val overview: String,
//    val adult: Boolean
//)

private suspend fun loadGenres(context: Context): List<Genre>{
    val genresUrl = URL("https://api.themoviedb.org/3/genre/movie/list?api_key=1bbcd34e71c300a0267ad1411ec2bc84&language=ru-Ru")
    val client = OkHttpClient()
    val request = Request.Builder()
            .get()
            .url(genresUrl)
            .build()
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            val content = response.body?.string() ?: return
            val results = JSONObject(content).getJSONArray("genres")
            val json = Json { ignoreUnknownKeys = true }
            genres =  json.decodeFromString<List<Genre>>(results.toString())
        }
    })
    return genres
}


private suspend fun getMovies(context: Context): List<MovieTest> {

    val getMoviesUrl = URL("https://api.themoviedb.org/3/movie/popular?api_key=1bbcd34e71c300a0267ad1411ec2bc84&language=ru-Ru&page=1")
    val client = OkHttpClient()
    val request = Request.Builder()
            .get()
            .url(getMoviesUrl)
            .build()
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            val content = response.body?.string() ?: return
            val results = JSONObject(content).getJSONArray("results")
//            val json = Json { ignoreUnknownKeys = true }
            movies =  jsonFormat.decodeFromString<List<MovieTest>>(results.toString())

        }
    })
    return movies
}

private suspend fun loadActors(id: Int): List<Actor> {
    var actors: ArrayList<Actor> = ArrayList<Actor>()
    val actorsUrl = URL("https://api.themoviedb.org/3/movie/" + id + "/credits?api_key=1bbcd34e71c300a0267ad1411ec2bc84&language=ru-Ru")
    val client = OkHttpClient()
    val request = Request.Builder()
            .get()
            .url(actorsUrl)
            .build()
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            val content = response.body?.string() ?: return
            val results = JSONObject(content).getJSONArray("cast")
//            val json = Json { ignoreUnknownKeys = true }
            actors =  jsonFormat.decodeFromString<ArrayList<Actor>>(results.toString())

        }
    })
    return actors
}

//private suspend fun loadGenres(context: Context): List<Genre> = withContext(Dispatchers.IO) {
//    val data = readAssetFileToString(context, "genres.json")
//    parseGenres(data)
//}

//internal fun parseGenres(data: String): List<Genre> {
//    val jsonGenres = jsonFormat.decodeFromString<List<JsonGenre>>(data)
//    return jsonGenres.map { Genre(id = it.id, name = it.name) }
//}

//private fun readAssetFileToString(context: Context, fileName: String): String {
//    val stream = context.assets.open(fileName)
//    return stream.bufferedReader().readText()
//}

//private suspend fun loadActors(context: Context): List<Actor> = withContext(Dispatchers.IO) {
//    val data = readAssetFileToString(context, "people.json")
//    parseActors(data)
//}

//internal fun parseActors(data: String): List<Actor> {
//    val jsonActors = jsonFormat.decodeFromString<List<JsonActor>>(data)
//    return jsonActors.map { Actor(id = it.id, name = it.name, picture = it.profilePicture) }
//}



internal suspend fun loadMovies(context: Context)  : List<Movie> {
    val scope = CoroutineScope(Dispatchers.Main)

    var m: List<Movie> = ArrayList<Movie>()
    scope.async {
        launch{ loadGenres(context) }
        launch { getMovies(context) }
    }.await()

    var actorsMap: ArrayList<Actor> = ArrayList<Actor>()

    m = parseMovies(movies, genres, actorsMap)




    return m
}

internal fun parseMovies(
        jsonMovies: List<MovieTest>,
    genres: List<Genre>,
    actors: List<Actor>
): List<Movie> {
    val genresMap = genres.associateBy { it.id }
    val actorsMap = actors.associateBy { it.id }

//    val jsonMovies = jsonFormat.decodeFromString<List<JsonMovie>>(data)

    var m =  jsonMovies.map { jsonMovie ->
        Movie(
            id = jsonMovie.id,
            title = jsonMovie.title,
            overview = jsonMovie.overview,
            poster = jsonMovie.poster,
            backdrop = jsonMovie.backdrop,
            ratings = jsonMovie.ratings,
            adult = jsonMovie.adult,
            runtime = jsonMovie.runtime,
            genres = jsonMovie.genres.map {
                genresMap[it] ?: throw IllegalArgumentException("Genre not found")
            },
            actors = jsonMovie.actors.map {
                actorsMap[it] ?: throw IllegalArgumentException("Actor not found")
            }
        )
    }

    return m

}