package com.example.apitest.RickandMortyAPI

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CharacterDao {
    @Insert
    suspend fun insertAllCharacters(characters: List<Character>)

    @Query("SELECT * FROM characters ORDER BY id ASC")
    fun getAllCharacters(): List<Character>

    @Query("DELETE FROM characters")
    suspend fun deleteAllCharacters()
}