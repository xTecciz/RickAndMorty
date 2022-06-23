package com.teckzi.rickandmorty.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.teckzi.rickandmorty.util.Constants.CHARACTER_DATABASE_TABLE

@Entity(tableName = CHARACTER_DATABASE_TABLE)
data class CharacterDbo(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo(name = "species") val species: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "origin") val origin: String,
    @ColumnInfo(name = "location") val location: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "episode") val episode: List<Int>
)