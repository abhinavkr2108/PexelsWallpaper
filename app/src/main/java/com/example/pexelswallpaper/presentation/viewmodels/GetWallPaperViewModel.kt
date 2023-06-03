package com.example.pexelswallpaper.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pexelswallpaper.domain.models.ImageResponse
import com.example.pexelswallpaper.domain.use_cases.GetWallPaperUseCase
import com.example.pexelswallpaper.presentation.states.WallPaperState
import com.example.pexelswallpaper.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetWallPaperViewModel @Inject constructor(private val getWallPaperUseCase: GetWallPaperUseCase): ViewModel() {
    private val _wallpaperListValue = MutableStateFlow(WallPaperState())
    val wallpaperListValue: StateFlow<WallPaperState> = _wallpaperListValue

    fun getWallpaper(query: String, perPage: Int) = viewModelScope.launch(Dispatchers.IO) {
        getWallPaperUseCase(query, perPage).collect{
            when(it){
                is ResponseState.Error -> {
                    _wallpaperListValue.value = WallPaperState(errorMessage = it.message.toString())
                }
                is ResponseState.Loading -> {
                    _wallpaperListValue.value = WallPaperState(isLoading = true)
                }
                is ResponseState.Success -> {
                    _wallpaperListValue.value = WallPaperState(wallPaperList = it.data!!)
                }
            }
        }
    }


}