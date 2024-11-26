package com.example.vk_android_course_hw2.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vk_android_course_hw2.data.ServiceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    private val apiKey = "hoSyGoWFce8InTMCA8uL0HLLp4CQBJ96"
    private val limit = 25
    private var isLoading = false

    init {
        loadGifs()
    }

    private fun loadGifs() {
        if (isLoading) return
        isLoading = true
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val images = withContext(Dispatchers.IO) {
                    val response = ServiceBuilder.giphyApi.getTrendingGifs(apiKey, limit)
                    response.data.map { Image(it.images.original.url) }
                }
                _uiState.value = UiState.Success(images)
            } catch (_: Exception) {
                _uiState.value = UiState.Error
            } finally {
                isLoading = false
            }
        }
    }

    fun retry() {
        loadGifs()
    }
}



sealed class UiState {
    object Loading : UiState()
    data class Success(val data: List<Image>) : UiState()
    object Error : UiState()
}
