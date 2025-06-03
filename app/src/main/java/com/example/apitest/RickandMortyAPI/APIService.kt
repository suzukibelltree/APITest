package com.example.apitest.RickandMortyAPI

import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("api/character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): CharacterResponse
}