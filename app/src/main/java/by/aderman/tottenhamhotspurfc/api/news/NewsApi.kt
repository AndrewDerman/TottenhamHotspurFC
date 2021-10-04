package by.aderman.tottenhamhotspurfc.api.news

import by.aderman.tottenhamhotspurfc.models.news.NewsResponse
import by.aderman.tottenhamhotspurfc.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getAllNews(
        @Query("qInTitle") title: String = Constants.NEWS_API_QUERY_TITLE,
        @Query("language") languageCode: String = Constants.NEWS_API_QUERY_LANGUAGE,
        @Query("sortBy") sortBy: String = Constants.NEWS_API_QUERY_SORT_BY,
        @Query("page") pageNumber: Int = Constants.NEWS_API_QUERY_PAGE,
        @Query("apiKey") apiKey: String = Constants.NEWS_API_KEY,
    ) : Response<NewsResponse>
}