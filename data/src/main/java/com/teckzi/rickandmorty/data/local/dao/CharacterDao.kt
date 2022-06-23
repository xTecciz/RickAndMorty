package com.teckzi.rickandmorty.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.teckzi.rickandmorty.data.local.model.CharacterDbo

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character_table ORDER BY id ASC")
    suspend fun getAllCharacters(): List<CharacterDbo>

    @Query("SELECT * FROM character_table WHERE id=:characterId")
    suspend fun getSelectedCharacter(characterId: Int): CharacterDbo

    @Query("SELECT * FROM character_table WHERE id IN (:characterIds)")
    suspend fun getCharacterList(characterIds: List<Int>): List<CharacterDbo>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCharacters(characters: List<CharacterDbo>)

    @Query("SELECT * FROM character_table WHERE name LIKE ifnull(:name, '%%') AND status LIKE ifnull(:status, '%%') AND species LIKE ifnull(:species, '%%') AND type LIKE ifnull(:type, '%%') AND gender LIKE ifnull(:gender, '%%') ORDER BY id ASC")
    suspend fun getFilteredCharacters(
        name: String?,
        status: String?,
        species: String?,
        type: String?,
        gender: String?
    ): List<CharacterDbo>
}