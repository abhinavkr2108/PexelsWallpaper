package com.example.pexelswallpaper.presentation.states

import com.example.pexelswallpaper.domain.models.ImageResponse

data class WallPaperState(
    var isLoading: Boolean = false,
    var wallPaperList: ImageResponse? = null,
    val errorMessage: String = ""
)
