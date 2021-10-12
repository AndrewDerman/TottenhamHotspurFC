package by.aderman.tottenhamhotspurfc.di

import android.app.Application
import androidx.room.Room
import by.aderman.tottenhamhotspurfc.adapters.news.NewsAdapter
import by.aderman.tottenhamhotspurfc.adapters.team.TeamAdapter
import by.aderman.tottenhamhotspurfc.api.football.FootballApiClient
import by.aderman.tottenhamhotspurfc.api.football.FootballInterceptor
import by.aderman.tottenhamhotspurfc.api.news.NewsApiClient
import by.aderman.tottenhamhotspurfc.database.ArticleDao
import by.aderman.tottenhamhotspurfc.database.Database
import by.aderman.tottenhamhotspurfc.repository.Repository
import by.aderman.tottenhamhotspurfc.util.MarginItemDecoration
import by.aderman.tottenhamhotspurfc.viewmodel.news.NewsViewModel
import by.aderman.tottenhamhotspurfc.viewmodel.player.PlayerViewModel
import by.aderman.tottenhamhotspurfc.viewmodel.team.TeamViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

val databaseModules = module {

    fun provideDatabase(application: Application): Database {
        return Room.databaseBuilder(application, Database::class.java, "Database")
            .build()
    }

    fun provideArticleDao(database: Database): ArticleDao {
        return database.getArticleDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideArticleDao(get()) }
}

val applicationModules = module {

    single { FootballInterceptor() }

    single {
        OkHttpClient.Builder().apply {
            addInterceptor(get<FootballInterceptor>())
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
        }.build()
    }

    single { NewsApiClient.newsApi }
    single { FootballApiClient.footballApi }
    single { Repository(articleDao = get(), newsApi = get(), footballApi = get()) }
    factory { NewsAdapter() }
    factory { TeamAdapter() }
    factory { MarginItemDecoration() }
}

val viewModelsModules = module {

    viewModel { NewsViewModel(get(), androidApplication()) }
    viewModel { TeamViewModel(get(), androidApplication()) }
    viewModel { PlayerViewModel(get(), androidApplication()) }
}