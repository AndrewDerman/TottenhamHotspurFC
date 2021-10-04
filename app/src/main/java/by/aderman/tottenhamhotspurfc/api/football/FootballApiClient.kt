package by.aderman.tottenhamhotspurfc.api.football

import by.aderman.tottenhamhotspurfc.util.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object FootballApiClient {

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(FootballInterceptor())
        readTimeout(30, TimeUnit.SECONDS)
    }.build()

    val footballApi: FootballApi by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.FOOTBALL_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FootballApi::class.java)
    }
}