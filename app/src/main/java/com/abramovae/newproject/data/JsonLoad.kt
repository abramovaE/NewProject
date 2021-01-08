package com.android.academy.fundamentals.homework.features.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.lifecycle.viewModelScope
import com.abramovae.newproject.BuildConfig
import com.abramovae.newproject.MainActivity
import com.abramovae.newproject.repo.LoadMoviesInt
import com.abramovae.newproject.viewModel.MoviesVM
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.create
import java.io.IOException
import java.net.URL
import java.util.concurrent.TimeUnit
import kotlin.random.Random


private val jsonFormat = Json { ignoreUnknownKeys = true }
//var movies: List<MovieTest> = ArrayList<MovieTest>()
//var genres: List<Genre> = ArrayList<Genre>()


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

//private suspend fun loadGenres(): List<Genre>{
//    val genresUrl = URL("https://api.themoviedb.org/3/genre/movie/list?api_key=1bbcd34e71c300a0267ad1411ec2bc84&language=ru-Ru")
//    val client = OkHttpClient()
//    val request = Request.Builder()
//            .get()
//            .url(genresUrl)
//            .build()
//    client.newCall(request).enqueue(object : Callback {
//        override fun onFailure(call: Call, e: IOException) {
//            e.printStackTrace()
//        }
//
//        override fun onResponse(call: Call, response: Response) {
//            val content = response.body?.string() ?: return
//            val results = JSONObject(content).getJSONArray("genres")
//            val json = Json { ignoreUnknownKeys = true }
//            genres =  json.decodeFromString<List<Genre>>(results.toString())
//            Log.d("tag", "genress: " + genres)
//        }
//    })
//    return genres
//}

private object RetrofitModule {
    private val json = Json {
        ignoreUnknownKeys = true
    }
    val httpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    @Suppress("EXPERIMENTAL_API_USAGE")
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org")
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .client(httpClient)
        .build()

    val loadMoviesApi: LoadMoviesInt = retrofit.create()
}


//private suspend fun getMovies(): List<MovieTest>  {

//    val getMoviesUrl = URL("https://api.themoviedb.org/3/movie/popular?api_key=1bbcd34e71c300a0267ad1411ec2bc84&language=ru-Ru&page=1")
//    val client = OkHttpClient()
//    val request = Request.Builder()
//            .get()
//            .url(getMoviesUrl)
//            .build()
//    client.newCall(request).enqueue(object : Callback {
//        override fun onFailure(call: Call, e: IOException) {
//            e.printStackTrace()
//
//        }
//
//        override fun onResponse(call: Call, response: Response) {
//            val content = response.body?.string() ?: return
//            val results = JSONObject(content).getJSONArray("results")
//            movies = jsonFormat.decodeFromString<List<MovieTest>>(results.toString())
//            Log.d("tag", "moviess: " + movies)
//
//
//        }
//    })
//    return movies
//}

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



internal suspend fun loadMovies()  : List<Movie> {

    lateinit var data: List<Movie>

    val scope = CoroutineScope(Dispatchers.Main)


    scope.async {
        launch {
//            getMovies()
        }
        launch {
//            loadGenres()
        }
    }.await()


    var actorsMap: ArrayList<Actor> = ArrayList<Actor>()
    return parseMovies(actorsMap)
}



private fun parseMovies(

    actors: List<Actor>
) : List<Movie> {
//    val genresMap = genres.associateBy { it.id }
//    val actorsMap = actors.associateBy { it.id }
////    Log.d("tag", "parse genres: " + genres)
////    Log.d("tag", "parse movies: " + movies)

    return ArrayList<Movie>()
}