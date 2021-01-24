package com.abramovae.newproject.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ActorDao {

    @Query("SELECT * FROM actordb WHERE uid = :actorId LIMIT 1")
    fun getActor(actorId: Int) : ActorDB


    @Query("SELECT * FROM actordb WHERE movies_ids LIKE :movieId")
    fun getActors(movieId: String) : List<ActorDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveActors(actors: List<ActorDB>)
}