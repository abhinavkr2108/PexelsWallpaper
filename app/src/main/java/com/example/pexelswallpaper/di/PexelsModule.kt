package com.example.pexelswallpaper.di

import com.example.pexelswallpaper.data.repository.PexelsRepositoryImpl
import com.example.pexelswallpaper.data.source.PexelsApi
import com.example.pexelswallpaper.domain.repository.PexelsRepository
import com.example.pexelswallpaper.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PexelsModule {

    @Provides
    @Singleton
    fun provideCoinGeckoApi(): PexelsApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PexelsApi::class.java)
    }

    @Provides
    @Singleton
    fun providePexelsRepository(api: PexelsApi): PexelsRepository {
        return PexelsRepositoryImpl(api)
    }


}