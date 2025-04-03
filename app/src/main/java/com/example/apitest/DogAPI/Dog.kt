package com.example.apitest.DogAPI

import kotlinx.serialization.Serializable

@Serializable
data class DogResponse(
    val message: List<String>,
    val status: String
)