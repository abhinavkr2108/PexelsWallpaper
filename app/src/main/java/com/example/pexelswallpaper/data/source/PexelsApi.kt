package com.example.pexelswallpaper.data.source

import com.example.pexelswallpaper.data.source.dto.ImageResponseDTO
import com.example.pexelswallpaper.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PexelsApi {

    @Headers("Authorization: ${Constants.API_KEY}")
    @GET("search")
    suspend fun getWallpaper(
        @Query("query") query: String,
        @Query("per_page") per_page: Int
    ): ImageResponseDTO
}
