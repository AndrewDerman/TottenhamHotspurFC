package by.aderman.tottenhamhotspurfc.data.repositories.news

import androidx.lifecycle.LiveData
import by.aderman.tottenhamhotspurfc.domain.common.Result
import by.aderman.tottenhamhotspurfc.domain.models.news.Article
import by.aderman.tottenhamhotspurfc.domain.repositories.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val localDataSource: NewsLocalDataSource,
    private val remoteDataSource: NewsRemoteDataSource
) : NewsRepository {

    override suspend fun getRemoteNews(page: Int): Result<List<Article>> =
        remoteDataSource.getRemoteArticles(page)

    override fun getBookmarks(): LiveData<List<Article>> = localDataSource.getArticles()

    override suspend fun saveArticle(article: Article) = localDataSource.saveArticle(article)

    override suspend fun deleteArticle(article: Article) = localDataSource.deleteArticle(article)
}