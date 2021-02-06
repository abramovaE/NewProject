package com.abramovae.newproject.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import kotlinx.serialization.SerialName

@Entity(tableName = "actordb")
class ActorDB(
    @PrimaryKey
    @ColumnInfo(name= "uid")
    var uid: Int,

    @ColumnInfo(name= "name")
    var name: String?,

    @ColumnInfo(name= "profile_path")
    var picture: String?,

    @ColumnInfo(name = "movies_ids")
    var moviesIds: List<Int>?

//    @Relation(parentColumn = "uid", entityColumn = "uid")
//    val moviesList: List<MovieDB>

) {
    override fun toString(): String {
        return ( uid.toString() + " " + name + " " + picture + " " + moviesIds)
    }
}
