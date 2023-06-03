package com.example.pexelswallpaper.data.source.dto

import com.example.pexelswallpaper.domain.models.ImageResponse

data class ImageResponseDTO(
    val next_page: String?,
    val page: Int,
    val per_page: Int,
    val photos: List<PhotoDTO>,
    val total_results: Int
){
    fun toImageResponse(): ImageResponse{
        return ImageResponse(
            next_page= next_page,
            page = page,
            per_page = per_page,
            photos = photos.map { it.toPhoto() },
            total_results = total_results
        )
    }
}