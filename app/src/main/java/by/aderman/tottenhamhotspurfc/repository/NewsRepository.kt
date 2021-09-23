package by.aderman.tottenhamhotspurfc.repository

import androidx.lifecycle.LiveData
import by.aderman.tottenhamhotspurfc.api.NewsApiClient
import by.aderman.tottenhamhotspurfc.models.Article
import by.aderman.tottenhamhotspurfc.database.ArticleDao

class NewsRepository(private val articleDao: ArticleDao) {

    suspend fun getAllNews(page: Int) = NewsApiClient.newsApi.getAllNews(pageNumber = page)

    fun getAllSavedArticles(): LiveData<List<Article>> = articleDao.getAllSavedArticles()

    suspend fun addArticleToSaved(article: Article) = articleDao.addArticleToSaved(article)

    suspend fun deleteSavedArticle(article: Article) = articleDao.deleteSavedArticle(article)
}