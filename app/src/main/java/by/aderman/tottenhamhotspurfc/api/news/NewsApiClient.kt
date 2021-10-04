package by.aderman.tottenhamhotspurfc.api.news

import by.aderman.tottenhamhotspurfc.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewsApiClient {

    val newsApi: NewsApi by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.NEWS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }
}