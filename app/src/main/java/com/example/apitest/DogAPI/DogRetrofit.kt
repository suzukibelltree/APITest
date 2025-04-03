package com.example.apitest.DogAPI

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DogRetrofit {
    private const val BASE_URL = "https://dog.ceo/api/"
    val api: DogAPIService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DogAPIService::class.java)
    }
}