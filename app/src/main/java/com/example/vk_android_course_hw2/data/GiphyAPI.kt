package com.example.vk_android_course_hw2.data

import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyAPI {
    @GET("v1/gifs/trending")
    suspend fun getTrendingGifs(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int = 25,
        @Query("offset") offset: Int = 0,
        @Query("rating") rating: String = "g"
    ): GiphyResponse
}
