package com.abramovae.newproject.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ActorDao {

    @Query("SELECT * FROM actordb WHERE uid = :actorId LIMIT 1")
    fun getActor(actorId: Int) : ActorDB


    @Query("SELECT * FROM actordb WHERE :movieId IN (movies_ids)")
    fun getActors(movieId: Int) : List<ActorDB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveActors(genres: List<ActorDB>)
}