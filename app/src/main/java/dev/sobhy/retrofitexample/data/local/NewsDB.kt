package dev.sobhy.retrofitexample.data.local

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.sobhy.retrofitexample.data.model.Article

@Database(entities = [Article::class], version = 1)
abstract class NewsDB : RoomDatabase() {
    abstract fun newsDao(): NewsDao

    companion object{
        @Volatile
        private lateinit var instance: NewsDB
        private val LOCK = Any()

        fun Application.initArticleDataBase(): NewsDB {
            return runCatching { instance }.getOrElse {
                synchronized(LOCK) {
                    createDatabase(this).also { instance = it }
                }
            }


        }

        fun getInstance() = instance

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NewsDB::class.java,
                "article_db"
            ).allowMainThreadQueries()
                .build()
    }
}