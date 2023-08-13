package dev.sobhy.retrofitexample.data.remote

import dev.sobhy.retrofitexample.data.model.ArticleNews
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApi {
    @GET("everything")
    suspend fun getAllNews(
        @Query("q")
        query: String,
        @Query("apiKey")
        apiKey: String = RetrofitClient.API_KEY
    ) : Response<ArticleNews>
}