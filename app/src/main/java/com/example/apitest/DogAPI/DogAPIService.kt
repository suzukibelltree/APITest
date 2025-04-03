package com.example.apitest.DogAPI

import retrofit2.http.GET
import retrofit2.http.Path

interface DogAPIService {
    @GET("breeds/image/random/{count}")
    suspend fun getDogImages(
        @Path("count") count: Int
    ): DogResponse
}