package com.teckzi.rickandmorty.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.teckzi.rickandmorty.data.local.dao.CharacterDao
import com.teckzi.rickandmorty.data.local.dao.EpisodeDao
import com.teckzi.rickandmorty.data.local.dao.LocationDao
import com.teckzi.rickandmorty.data.local.model.CharacterDbo
import com.teckzi.ricks.data.local.model.EpisodeDbo
import com.teckzi.ricks.data.local.model.LocationDbo

@Database(entities = [CharacterDbo::class, LocationDbo::class, EpisodeDbo::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseConverter::class)
abstract class RickAndMortyDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun locationDao(): LocationDao
    abstract fun episodeDao(): EpisodeDao

//    companion object {
//        fun create(context: Context, useInMemory: Boolean): RickAndMortyDatabase {
//            val databaseBuilder = if (useInMemory) {
//                Room.inMemoryDatabaseBuilder(context, RickAndMortyDatabase::class.java)
//            } else {
//                Room.databaseBuilder(context, RickAndMortyDatabase::class.java, "test_database.db")
//            }
//            return databaseBuilder
//                .fallbackToDestructiveMigration()
//                .build()
//        }
//    }
}