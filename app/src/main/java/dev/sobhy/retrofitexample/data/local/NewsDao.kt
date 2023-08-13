package dev.sobhy.retrofitexample.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import dev.sobhy.retrofitexample.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Insert
    fun insertArticle(article: Article)
    @Delete
    fun deleteArticle(article: Article)
    @Query("select * from article")
    fun getSavedArticle(): Flow<List<Article>>
    @Query("DELETE FROM article")
    suspend fun deleteAllArticle()
}