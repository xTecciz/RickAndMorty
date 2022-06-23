package com.teckzi.ricks.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.teckzi.rickandmorty.util.Constants.LOCATION_DATABASE_TABLE

@Entity(tableName = LOCATION_DATABASE_TABLE)
data class LocationDbo(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "type") var type: String,
    @ColumnInfo(name = "dimension") var dimension: String,
    @ColumnInfo(name = "characters") var characters: List<Int>?
)