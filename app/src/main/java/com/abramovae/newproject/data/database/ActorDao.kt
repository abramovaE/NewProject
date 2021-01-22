package com.abramovae.newproject.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ActorDao {
    @Query("SELECT * FROM actordb")
    fun getActors(movieId: Int) : List<ActorDB>

    @Insert
    fun saveActors(genres: List<ActorDB>)
}