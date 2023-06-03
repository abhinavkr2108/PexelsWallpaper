package com.example.pexelswallpaper.domain.use_cases

import com.example.pexelswallpaper.domain.models.ImageResponse
import com.example.pexelswallpaper.domain.repository.PexelsRepository
import com.example.pexelswallpaper.utils.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetWallPaperUseCase @Inject constructor(private val repository: PexelsRepository) {

    operator fun invoke(query: String, perPage: Int): Flow<ResponseState<ImageResponse>> = flow {
        try {
            emit(ResponseState.Loading())
            val wallPaperList = repository.getWallPaper(query, perPage).toImageResponse()
            emit(ResponseState.Success(data = wallPaperList))
        }
        catch (e: HttpException){
            emit(ResponseState.Error(message = "${e.localizedMessage}"))
        }
        catch (e: IOException){
            emit(ResponseState.Error(message = "${e.message}"))
        }
    }
}