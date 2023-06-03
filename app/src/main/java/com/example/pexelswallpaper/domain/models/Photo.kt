package com.example.pexelswallpaper.domain.models

import com.example.pexelswallpaper.data.source.dto.SrcDTO

data class Photo(
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
)