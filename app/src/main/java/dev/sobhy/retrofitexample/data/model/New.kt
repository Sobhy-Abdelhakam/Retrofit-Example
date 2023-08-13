package dev.sobhy.retrofitexample.data.model

data class New(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)