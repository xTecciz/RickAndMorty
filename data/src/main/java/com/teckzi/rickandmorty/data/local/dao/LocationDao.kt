package com.teckzi.rickandmorty.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.teckzi.ricks.data.local.model.LocationDbo

@Dao
interface LocationDao {

    @Query("SELECT * FROM location_table ORDER BY id ASC")
    suspend fun getAllLocations(): List<LocationDbo>

    @Query("SELECT * FROM location_table WHERE id=:locationId")
    suspend fun getSelectedLocation(locationId: Int): LocationDbo

    @Query("SELECT * FROM location_table WHERE name=:locationName")
    suspend fun getSelectedLocationByName(locationName: String): LocationDbo?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLocations(locations: List<LocationDbo>)

    @Query("SELECT * FROM location_table WHERE name LIKE ifnull(:name, '%%') AND type LIKE ifnull(:type, '%%') AND dimension LIKE ifnull(:dimension, '%%') ORDER BY id ASC")
    suspend fun getFilteredLocations(
        name: String?,
        type: String?,
        dimension: String?
    ): List<LocationDbo>
}