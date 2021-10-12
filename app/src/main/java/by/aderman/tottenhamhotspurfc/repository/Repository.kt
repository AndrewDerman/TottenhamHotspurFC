package by.aderman.tottenhamhotspurfc.repository

import androidx.lifecycle.LiveData
import by.aderman.tottenhamhotspurfc.api.football.FootballApi
import by.aderman.tottenhamhotspurfc.api.news.NewsApi
import by.aderman.tottenhamhotspurfc.database.ArticleDao
import by.aderman.tottenhamhotspurfc.models.news.Article

class Repository(
    private val articleDao: ArticleDao,
    private val newsApi: NewsApi,
    private val footballApi: FootballApi
) {

    suspend fun getAllNews(page: Int) = newsApi.getAllNews(pageNumber = page)

    fun getAllSavedArticles(): LiveData<List<Article>> = articleDao.getAllSavedArticles()

    suspend fun addArticleToSaved(article: Article) = articleDao.addArticleToSaved(article)

    suspend fun deleteSavedArticle(article: Article) = articleDao.deleteSavedArticle(article)

    suspend fun getTeamSquad() = footballApi.getTeamSquad()

    suspend fun getPlayerStatistics(playerId: Int) =
        footballApi.getPlayerStatistics(playerId = playerId)
}