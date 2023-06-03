package com.example.pexelswallpaper.data.source.dto

import com.example.pexelswallpaper.domain.models.Photo

data class PhotoDTO(
    val alt: String,
    val avg_color: String,
    val height: Int,
    val id: Int,
    val liked: Boolean,
    val photographer: String,
    val photographer_id: Int,
    val photographer_url: String,
    val src: SrcDTO,
    val url: String,
    val width: Int
){
    fun toPhoto(): Photo{
        return Photo(
            alt = alt,
            avg_color = avg_color,
            height = height,
            id = id,
            liked = liked,
            photographer = photographer,
            photographer_id = photographer_id,
            photographer_url = photographer_url,
            src = src,
            url = url,
            width = width
        )
    }
}