package com.example.apitest.RickandMortyAPI

import android.icu.text.IDNA

data class Character(
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