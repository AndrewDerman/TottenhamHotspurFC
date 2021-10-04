package by.aderman.tottenhamhotspurfc.repository

import androidx.lifecycle.LiveData
import by.aderman.tottenhamhotspurfc.api.football.FootballApiClient
import by.aderman.tottenhamhotspurfc.api.news.NewsApiClient
import by.aderman.tottenhamhotspurfc.models.news.Article
import by.aderman.tottenhamhotspurfc.database.ArticleDao

class Repository {

    lateinit var articleDao: ArticleDao

    suspend fun getAllNews(page: Int) = NewsApiClient.newsApi.getAllNews(pageNumber = page)

    fun getAllSavedArticles(): LiveData<List<Article>> = articleDao.getAllSavedArticles()

    suspend fun addArticleToSaved(article: Article) = articleDao.addArticleToSaved(article)

    suspend fun deleteSavedArticle(article: Article) = articleDao.deleteSavedArticle(article)

    suspend fun getTeamSquad() = FootballApiClient.footballApi.getTeamSquad()

    suspend fun getPlayerStatistics(playerId: Int) =
        FootballApiClient.footballApi.getPlayerStatistics(playerId = playerId)
}