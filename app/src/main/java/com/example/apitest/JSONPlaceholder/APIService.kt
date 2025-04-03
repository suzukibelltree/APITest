package com.example.apitest.JSONPlaceholder

import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("posts")
    suspend fun getPosts(
        @Query("_page") page: Int,
        @Query("_limit") limit: Int
    ): List<Post>
}