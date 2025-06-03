package com.example.apitest.RickandMortyAPI

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters(characters: List<Character>)

    @Query("SELECT * FROM characters ORDER BY id ASC")
    fun getAllCharacters(): PagingSource<Int, Character>

    @Query("SELECT * FROM characters ORDER BY id ASC")
    fun getAllCharactersList(): List<Character>

    @Query("DELETE FROM characters")
    suspend fun deleteAllCharacters()
}