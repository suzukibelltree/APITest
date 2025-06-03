package com.example.apitest.RickandMortyAPI

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

data class Info(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)

data class CharacterResponse(
    val info: Info,
    val results: List<Character>
)