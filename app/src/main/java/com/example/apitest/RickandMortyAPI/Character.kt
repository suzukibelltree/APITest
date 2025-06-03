package com.example.apitest.RickandMortyAPI

import android.icu.text.IDNA
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class Character(
    @PrimaryKey
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val image: String,
)

data class CharacterResponse(
    val info: IDNA.Info,
    val results: List<Character>
)