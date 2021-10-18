package by.aderman.tottenhamhotspurfc.data.api.football

import by.aderman.tottenhamhotspurfc.utils.Constants
import okhttp3.OkHttpClient
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FootballApiClient : KoinComponent {

    private val client by inject<OkHttpClient>()

    val footballApi: FootballApi by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.FOOTBALL_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FootballApi::class.java)
    }
}