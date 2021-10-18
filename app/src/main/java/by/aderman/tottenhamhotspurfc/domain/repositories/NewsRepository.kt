package by.aderman.tottenhamhotspurfc.domain.repositories

import androidx.lifecycle.LiveData
import by.aderman.tottenhamhotspurfc.domain.common.Result
import by.aderman.tottenhamhotspurfc.domain.models.news.Article

interface NewsRepository {

    suspend fun getRemoteNews(page: Int): Result<List<Article>>

    fun getBookmarks(): LiveData<List<Article>>

    suspend fun saveArticle(article: Article)

    suspend fun deleteArticle(article: Article)
}