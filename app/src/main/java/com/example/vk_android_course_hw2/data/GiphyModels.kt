package com.example.vk_android_course_hw2.data

data class GiphyResponse(
    val data: List<GiphyGif>
)

data class GiphyGif(
    val id: String,
    val images: GiphyImages
)

data class GiphyImages(
    val original: GiphyImage
)

data class GiphyImage(
    val url: String
)
