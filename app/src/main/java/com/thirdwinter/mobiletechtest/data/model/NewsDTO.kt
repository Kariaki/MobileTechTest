package com.thirdwinter.mobiletechtest.data.model

data class NewsDTO(
    val articles: List<ArticleDTO> = listOf(),
    val status: String?,
    val totalResults: Int?
)