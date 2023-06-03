package com.example.pexelswallpaper.domain.repository

import com.example.pexelswallpaper.data.source.dto.ImageResponseDTO

interface PexelsRepository {

    suspend fun getWallPaper(
        query: String,
        perPage: Int
    ): ImageResponseDTO
}