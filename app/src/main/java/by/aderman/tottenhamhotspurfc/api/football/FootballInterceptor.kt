package by.aderman.tottenhamhotspurfc.api.football

import by.aderman.tottenhamhotspurfc.BuildConfig
import by.aderman.tottenhamhotspurfc.util.Constants
import okhttp3.Interceptor
import okhttp3.Response

class FootballInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("x-rapidapi-key", BuildConfig.FOOTBALL_API_KEY)
            .build()
        return chain.proceed(request)
    }
}