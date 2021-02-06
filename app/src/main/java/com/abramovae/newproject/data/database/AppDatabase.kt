package com.abramovae.newproject.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ActorDB::class, GenreDB::class, MovieDB::class], version = 5)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun actorDao(): ActorDao
    abstract fun genreDao(): GenreDao



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