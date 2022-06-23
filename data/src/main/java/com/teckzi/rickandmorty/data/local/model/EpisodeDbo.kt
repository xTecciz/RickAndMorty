package com.teckzi.ricks.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.teckzi.rickandmorty.util.Constants.EPISODE_DATABASE_TABLE

@Entity(tableName = EPISODE_DATABASE_TABLE)
data class EpisodeDbo(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "air_date") var airDate: String,
    @ColumnInfo(name = "episode") var episode: String,
    @ColumnInfo(name = "characters") var characters: List<Int>
)