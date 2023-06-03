package com.example.pexelswallpaper.data.source.dto

import com.example.pexelswallpaper.domain.models.Src

data class SrcDTO(
    val landscape: String,
    val large: String,
    val large2x: String,
    val medium: String,
    val original: String,
    val portrait: String,
    val small: String,
    val tiny: String
){
    fun toSrc(): Src{
        return Src(
            landscape = landscape,
            large = large,
            large2x = large2x,
            medium = medium,
            original = original,
            portrait = portrait,
            small = small,
            tiny = tiny
        )
    }
}