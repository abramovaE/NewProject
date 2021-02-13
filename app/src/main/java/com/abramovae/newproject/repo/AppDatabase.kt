package com.abramovae.newproject.repo


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.abramovae.newproject.data.GenreDao
import com.abramovae.newproject.data.ActorDao
import com.abramovae.newproject.data.MovieDao
import com.android.academy.fundamentals.homework.features.data.Actor
import com.android.academy.fundamentals.homework.features.data.Genre
import com.android.academy.fundamentals.homework.features.data.Movie

@Database(entities = [Movie::class, Actor::class, Genre::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val movieDao: MovieDao
    abstract val actorDao: ActorDao
    abstract val genreDao: GenreDao



    companion object {
        private const val DATABASE_NAME = "Movies.db"

        fun create(applicationContext: Context): AppDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

    }
}

