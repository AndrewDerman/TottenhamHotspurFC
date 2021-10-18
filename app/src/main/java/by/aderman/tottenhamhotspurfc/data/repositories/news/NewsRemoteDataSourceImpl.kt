package by.aderman.tottenhamhotspurfc.data.repositories.news

import by.aderman.tottenhamhotspurfc.data.api.news.NewsApi
import by.aderman.tottenhamhotspurfc.data.mappers.news.NewsResponseMapper
import by.aderman.tottenhamhotspurfc.domain.common.Result
import by.aderman.tottenhamhotspurfc.domain.models.news.Article

class NewsRemoteDataSourceImpl(
    private val api: NewsApi,
    private val mapper: NewsResponseMapper
) : NewsRemoteDataSource {

    override suspend fun getRemoteArticles(page: Int): Result<List<Article>> {
        val response = api.getAllNews(pageNumber = page)
        return if (response.isSuccessful) {
            Result.Success(response.body()?.let { mapper.toArticleList(it) })
        } else {
            Result.Error(response.message())
        }
    }
}