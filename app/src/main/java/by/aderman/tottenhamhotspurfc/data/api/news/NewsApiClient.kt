package by.aderman.tottenhamhotspurfc.data.api.news

import by.aderman.tottenhamhotspurfc.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewsApiClient{

    val newsApi: NewsApi by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.NEWS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }
}