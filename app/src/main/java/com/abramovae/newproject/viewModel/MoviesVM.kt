package com.abramovae.newproject.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.abramovae.newproject.data.RetrofitModule
import com.abramovae.newproject.data.database.ActorDB
import com.abramovae.newproject.data.database.GenreDB
import com.abramovae.newproject.data.database.MovieDB
import com.abramovae.newproject.data.database.Repository
import com.abramovae.newproject.repo.LoadMoviesInt
import com.android.academy.fundamentals.homework.features.data.Actor
import com.android.academy.fundamentals.homework.features.data.Genre
import com.android.academy.fundamentals.homework.features.data.Movie
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MoviesVM(private val loadMoviesApi: LoadMoviesInt, private val repo: Repository): ViewModel() {
    private val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
        _exText.value = "the loading was failed"
    }

    private val _exText = MutableLiveData<String>()
    val exText get() = _exText

    private val _selectedMovie = MutableLiveData<Movie>()
    val selectedMovie get() = _selectedMovie

    private val _movies = MutableLiveData<List<Movie>>()
    val movies get() = _movies

    private val _genres = MutableLiveData<List<Genre>>()
    val genres get() = _genres

    lateinit var genresList: List<Genre>

    fun select(movie: Movie) {
        viewModelScope.launch(handler) {
            var actorsDb: List<ActorDB> = repo.getActors(movie.id)
            movie.actors = convertActors(actorsDb)
//            _selectedMovie.value = movie
            var actors = getActors(movie.id)
            movie.actors = actors
            var convertedActors = convertActors(actors, movie.id)
            repo.saveActors(convertedActors)
            _selectedMovie.value = movie
        }
    }



    fun load() {
        lateinit var movies: List<Movie>
        viewModelScope.launch(handler) {
            withContext(Dispatchers.IO){
                var movDb: List<MovieDB> = repo.getAllMovies()
                var genDB: List<GenreDB> = repo.getAllGenres()
                movies = movDb.map {
                    Movie(it.uid,
                    it.title,
                    it.overview,
                    it.poster,
                    it.backdrop,
                    it.ratings,
                    it.adult,
                    it.runtime,
                    it.genreIds,
                    getGenres(it.genreIds, conversGenres(genDB)),
                    kotlin.collections.emptyList<com.android.academy.fundamentals.homework.features.data.Actor>()
                    )
                }
            }
            _movies.value = movies

            withContext(Dispatchers.IO) {
                genresList = loadMoviesApi.loadGenres().genres
                movies = loadMoviesApi.getMovies().movies.map {
                    Movie(
                        it,
                        getGenres(it.genreIds),
                        kotlin.collections.emptyList<com.android.academy.fundamentals.homework.features.data.Actor>()
                    )
                }
            }

            withContext(Dispatchers.IO){
                val byRating: Comparator<Movie> = Comparator.comparing {m-> m.ratings}
                var m = movies.sortedWith(byRating).reversed().get(0)
                repo.showNotification(m)
            }

            var moviesdb: List<MovieDB> = convertMovies(movies)
            var genresDb: List<GenreDB> = convertGenres(genresList)
            repo.saveAllMovies(moviesdb)
            repo.saveAllGenres(genresDb)
            _movies.value = movies
        }
    }


    fun getGenres(genreIds: List<Int>): List<Genre>{
        return genresList.filter { genreIds.contains(it.id) }
    }

    fun getGenres(genreIds: List<Int>, genres: List<Genre>): List<Genre>{
        return genres.filter { genreIds.contains(it.id) }
    }

    fun conversGenres(genresDb: List<GenreDB>): List<Genre>{
        return genresDb.map {
            Genre(
                it.uid, it.name
            )
        }
    }

    fun convertGenres(genres: List<Genre>): List<GenreDB>{
        return genres.map {
            GenreDB(
                it.id, it.name
            )
        }
    }

    fun convertMovies(movies: List<Movie>) : List<MovieDB>{
        return movies.map {
            MovieDB(
                it.id,
                it.title,
                it.overview,
                it.poster,
                it.backdrop,
                it.ratings,
                it.adult,
                it.runtime,
                it.genreIds,
            )
        }
    }

    fun convertActors(actorsDb: List<ActorDB>) : List<Actor>{
        return actorsDb.map {
            Actor(
                it.uid, it.name, it.picture
            )
        }
    }

    suspend fun convertActors(actors: List<Actor>, movieId: Int) : List<ActorDB>{

        return actors.map {
            var moviesIds = ArrayList<Int>(1)
            var actor = repo.getActor(it.id)
            if(actor != null){
                moviesIds = repo.getActor(it.id).moviesIds as ArrayList<Int>
                if(moviesIds == null){
                    moviesIds = ArrayList<Int>(1)
                }
            }
            if(!moviesIds.contains(movieId)) {
                moviesIds.add(movieId)
            }
            ActorDB(
                it.id, it.name, it.picture,  moviesIds
            )
        }
    }

    suspend fun getActors(movieId: Int): List<Actor>{
        return loadMoviesApi.loadMovieActors(movieId).actors
    }


    fun setMovies(m: List<Movie>) {
        _movies.value = m
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(context: Context) :
        ViewModelProvider.NewInstanceFactory() {
        private val appContext = context.applicationContext
        private val repo = Repository(context.applicationContext)

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            when (modelClass) {
                MoviesVM::class.java -> MoviesVM(RetrofitModule.loadMoviesApi, repo) as T
                else -> throw IllegalArgumentException()
            }
            return MoviesVM(RetrofitModule.loadMoviesApi, repo) as T
        }
    }
}