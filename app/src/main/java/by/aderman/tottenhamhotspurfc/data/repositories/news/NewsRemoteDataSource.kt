package by.aderman.tottenhamhotspurfc.data.repositories.news

import by.aderman.tottenhamhotspurfc.domain.common.Result
import by.aderman.tottenhamhotspurfc.domain.models.news.Article

interface NewsRemoteDataSource {

    suspend fun getRemoteArticles(page: Int): Result<List<Article>>
}