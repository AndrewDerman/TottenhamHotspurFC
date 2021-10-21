package by.aderman.tottenhamhotspurfc.di

import android.app.Application
import androidx.room.Room
import by.aderman.tottenhamhotspurfc.data.api.football.FootballApiClient
import by.aderman.tottenhamhotspurfc.data.api.football.FootballInterceptor
import by.aderman.tottenhamhotspurfc.data.api.news.NewsApiClient
import by.aderman.tottenhamhotspurfc.data.db.ArticleDao
import by.aderman.tottenhamhotspurfc.data.db.Database
import by.aderman.tottenhamhotspurfc.data.mappers.news.ArticleLocalMapper
import by.aderman.tottenhamhotspurfc.data.mappers.news.NewsResponseMapper
import by.aderman.tottenhamhotspurfc.data.mappers.season.StandingsResponseMapper
import by.aderman.tottenhamhotspurfc.data.mappers.season.TopAssistsResponseMapper
import by.aderman.tottenhamhotspurfc.data.mappers.season.TopScorersResponseMapper
import by.aderman.tottenhamhotspurfc.data.mappers.team.PlayerResponseMapper
import by.aderman.tottenhamhotspurfc.data.mappers.team.TeamResponseMapper
import by.aderman.tottenhamhotspurfc.data.repositories.news.*
import by.aderman.tottenhamhotspurfc.data.repositories.season.SeasonRemoteDataSource
import by.aderman.tottenhamhotspurfc.data.repositories.season.SeasonRemoteDataSourceImpl
import by.aderman.tottenhamhotspurfc.data.repositories.season.SeasonRepositoryImpl
import by.aderman.tottenhamhotspurfc.data.repositories.team.TeamRemoteDataSource
import by.aderman.tottenhamhotspurfc.data.repositories.team.TeamRemoteDataSourceImpl
import by.aderman.tottenhamhotspurfc.data.repositories.team.TeamRepositoryImpl
import by.aderman.tottenhamhotspurfc.domain.repositories.NewsRepository
import by.aderman.tottenhamhotspurfc.domain.repositories.SeasonRepository
import by.aderman.tottenhamhotspurfc.domain.repositories.TeamRepository
import by.aderman.tottenhamhotspurfc.domain.usecases.news.DeleteArticleUseCase
import by.aderman.tottenhamhotspurfc.domain.usecases.news.GetBookmarksUseCase
import by.aderman.tottenhamhotspurfc.domain.usecases.news.GetNewsUseCase
import by.aderman.tottenhamhotspurfc.domain.usecases.news.SaveArticleUseCase
import by.aderman.tottenhamhotspurfc.domain.usecases.season.GetLeagueTableUseCase
import by.aderman.tottenhamhotspurfc.domain.usecases.season.GetTopAssistsUseCase
import by.aderman.tottenhamhotspurfc.domain.usecases.season.GetTopScorersUseCase
import by.aderman.tottenhamhotspurfc.domain.usecases.team.GetPlayerStatisticUseCase
import by.aderman.tottenhamhotspurfc.domain.usecases.team.GetTeamSquadUseCase
import by.aderman.tottenhamhotspurfc.presentation.adapters.news.NewsAdapter
import by.aderman.tottenhamhotspurfc.presentation.adapters.season.AssistsAdapter
import by.aderman.tottenhamhotspurfc.presentation.adapters.season.GoalsAdapter
import by.aderman.tottenhamhotspurfc.presentation.adapters.season.TableAdapter
import by.aderman.tottenhamhotspurfc.presentation.adapters.team.TeamAdapter
import by.aderman.tottenhamhotspurfc.utils.MarginItemDecoration
import by.aderman.tottenhamhotspurfc.presentation.viewmodels.news.NewsViewModel
import by.aderman.tottenhamhotspurfc.presentation.viewmodels.season.SeasonViewModel
import by.aderman.tottenhamhotspurfc.presentation.viewmodels.team.TeamViewModel
import by.aderman.tottenhamhotspurfc.utils.Constants
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

val databaseModules = module {

    fun provideDatabase(application: Application): Database {
        return Room.databaseBuilder(application, Database::class.java, Constants.DATABASE_NAME)
            .build()
    }

    fun provideArticleDao(database: Database): ArticleDao {
        return database.getArticleDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideArticleDao(get()) }
}

val apiModules = module {

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
}

val repositoryModules = module {

    factory { ArticleLocalMapper() }
    factory { NewsResponseMapper() }
    factory { PlayerResponseMapper() }
    factory { TeamResponseMapper() }
    factory { StandingsResponseMapper() }
    factory { TopScorersResponseMapper() }
    factory { TopAssistsResponseMapper() }

    single<NewsLocalDataSource> {
        NewsLocalDataSourceImpl(
            articleDao = get(),
            mapper = get()
        )
    }

    single<NewsRemoteDataSource> {
        NewsRemoteDataSourceImpl(
            api = get(),
            mapper = get()
        )
    }

    single<NewsRepository> {
        NewsRepositoryImpl(
            localDataSource = get(),
            remoteDataSource = get()
        )
    }

    single<TeamRemoteDataSource> {
        TeamRemoteDataSourceImpl(
            api = get(),
            teamResponseMapper = get(),
            playerResponseMapper = get()
        )
    }

    single<TeamRepository> { TeamRepositoryImpl(get()) }

    single<SeasonRemoteDataSource> {
        SeasonRemoteDataSourceImpl(
            api = get(),
            standingsResponseMapper = get(),
            topScorersResponseMapper = get(),
            topAssistsResponseMapper = get()
        )
    }

    single<SeasonRepository> { SeasonRepositoryImpl(get()) }
}

val applicationModules = module {

    factory { NewsAdapter() }
    factory { TeamAdapter() }
    factory { TableAdapter() }
    factory { GoalsAdapter() }
    factory { AssistsAdapter() }
    factory { MarginItemDecoration() }

    factory { DeleteArticleUseCase(get()) }
    factory { GetBookmarksUseCase(get()) }
    factory { GetNewsUseCase(get()) }
    factory { SaveArticleUseCase(get()) }

    factory { GetPlayerStatisticUseCase(get()) }
    factory { GetTeamSquadUseCase(get()) }

    factory { GetLeagueTableUseCase(get()) }
    factory { GetTopScorersUseCase(get()) }
    factory { GetTopAssistsUseCase(get()) }
}

val viewModelsModules = module {

    viewModel {
        NewsViewModel(
            getNewsUseCase = get(),
            getBookmarksUseCase = get(),
            saveArticleUseCase = get(),
            deleteArticleUseCase = get(),
            androidApplication()
        )
    }

    viewModel {
        TeamViewModel(
            getTeamSquadUseCase = get(),
            getPlayerStatisticUseCase = get(),
            application = androidApplication()
        )
    }

    viewModel {
        SeasonViewModel(
            getLeagueTableUseCase = get(),
            getTopScorersUseCase = get(),
            getTopAssistsUseCase = get(),
            application = androidApplication()
        )
    }
}