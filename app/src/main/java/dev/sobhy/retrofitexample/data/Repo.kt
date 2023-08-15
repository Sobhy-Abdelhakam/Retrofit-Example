package dev.sobhy.retrofitexample.data

import dev.sobhy.retrofitexample.data.local.NewsDB
import dev.sobhy.retrofitexample.data.model.Article
import dev.sobhy.retrofitexample.data.remote.NewsApi
import dev.sobhy.retrofitexample.data.remote.RetrofitClient

class Repo(
    private val api: NewsApi = RetrofitClient().newsApi,
    private val db: NewsDB = NewsDB.getInstance()
) {
    suspend fun getNews(q: String) = api.getAllNews(q).body()?.articles.orEmpty()

    suspend fun insertNew(article: Article) = db.newsDao().insertArticle(article)
    suspend fun deleteNew(article: Article) = db.newsDao().deleteArticle(article)
    suspend fun deleteAllNew() = db.newsDao().deleteAllArticle()
    suspend fun getNewsFromDb() = db.newsDao().getSavedArticle()

    suspend fun searchForArticle(search: String) = db.newsDao().getArticleByTitle(search)

}