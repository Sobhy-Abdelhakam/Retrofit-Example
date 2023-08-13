package dev.sobhy.retrofitexample.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitClient {
    companion object {
        const val API_KEY = "e7c793586816468f806c09871a97282d"
        private const val BASE_URL = "https://newsapi.org/v2/"

        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

    val newsApi : NewsApi by lazy {
        retrofit.create(NewsApi::class.java)
    }
}