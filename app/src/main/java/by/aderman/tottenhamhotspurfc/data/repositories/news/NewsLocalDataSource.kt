package by.aderman.tottenhamhotspurfc.data.repositories.news

import androidx.lifecycle.LiveData
import by.aderman.tottenhamhotspurfc.domain.models.news.Article
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {

    fun getArticles(): LiveData<List<Article>>
    suspend fun saveArticle(article: Article)
    suspend fun deleteArticle(article: Article)
}