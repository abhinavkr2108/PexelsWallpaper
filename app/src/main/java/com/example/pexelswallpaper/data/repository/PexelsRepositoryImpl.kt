package com.example.pexelswallpaper.data.repository

import com.example.pexelswallpaper.data.source.PexelsApi
import com.example.pexelswallpaper.data.source.dto.ImageResponseDTO
import com.example.pexelswallpaper.domain.repository.PexelsRepository
import javax.inject.Inject

class PexelsRepositoryImpl @Inject constructor(private val pexelsApi: PexelsApi): PexelsRepository {
    override suspend fun getWallPaper(query: String, perPage: Int): ImageResponseDTO{
        return pexelsApi.getWallpaper(query, perPage)
    }
}