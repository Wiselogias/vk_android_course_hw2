package com.example.vk_android_course_hw2.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.vk_android_course_hw2.domain.MyViewModel
import com.example.vk_android_course_hw2.domain.UiState
import com.example.vk_android_course_hw2.ui.theme.Vk_android_course_hw2Theme
import com.example.vk_android_course_hw2.ui.theme.Vk_android_course_hw2Theme

class MainActivity : ComponentActivity() {

    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Vk_android_course_hw2Theme {
                val uiState = viewModel.uiState.collectAsState()

                when (uiState.value) {
                    is UiState.Loading -> {
                        LoadingView()
                    }
                    is UiState.Success -> {
                        val images = (uiState.value as? UiState.Success)?.data
                        images?.let { ImageGrid(it) }
                    }
                    is UiState.Error -> {
                        ErrorView(retry = { viewModel.retry() })
                    }
                }
            }
        }
    }
}
