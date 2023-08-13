package dev.sobhy.retrofitexample

import android.app.Application
import dev.sobhy.retrofitexample.data.local.NewsDB.Companion.initArticleDataBase

class RetrofitExample: Application() {
    override fun onCreate() {
        super.onCreate()
        initArticleDataBase()
    }
}