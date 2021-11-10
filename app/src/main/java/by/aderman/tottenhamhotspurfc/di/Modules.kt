package by.aderman.tottenhamhotspurfc.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import by.aderman.tottenhamhotspurfc.data.api.football.FootballApiClient
import by.aderman.tottenhamhotspurfc.data.api.football.FootballInterceptor
import by.aderman.tottenhamhotspurfc.data.api.news.NewsApiClient
import by.aderman.tottenhamhotspurfc.data.db.*
import by.aderman.tottenhamhotspurfc.data.mappers.fixtures.FixtureInfoResponseMapper
import by.aderman.tottenhamhotspurfc.data.mappers.fixtures.FixturesLocalMapper
import by.aderman.tottenhamhotspurfc.data.mappers.fixtures.FixturesResponseMapper
import by.aderman.tottenhamhotspurfc.data.mappers.fixtures.ResultsLocalMapper
import by.aderman.tottenhamhotspurfc.data.mappers.news.ArticleLocalMapper
import by.aderman.tottenhamhotspurfc.data.mappers.news.NewsResponseMapper
import by.aderman.tottenhamhotspurfc.data.mappers.season.StandingsResponseMapper
import by.aderman.tottenhamhotspurfc.data.mappers.season.TopAssistsResponseMapper
import by.aderman.tottenhamhotspurfc.data.mappers.season.TopScorersResponseMapper
import by.aderman.tottenhamhotspurfc.data.mappers.team.PlayerLocalMapper
import by.aderman.tottenhamhotspurfc.data.mappers.team.PlayerResponseMapper
import by.aderman.tottenhamhotspurfc.data.mappers.team.TeamResponseMapper
import by.aderman.tottenhamhotspurfc.data.repositories.fixtures.*
import by.aderman.tottenhamhotspurfc.data.repositories.news.*
import by.aderman.tottenhamhotspurfc.data.repositories.season.SeasonRemoteDataSource
import by.aderman.tottenhamhotspurfc.data.repositories.season.SeasonRemoteDataSourceImpl
import by.aderman.tottenhamhotspurfc.data.repositories.season.SeasonRepositoryImpl
import by.aderman.tottenhamhotspurfc.data.repositories.team.*
import by.aderman.tottenhamhotspurfc.domain.repositories.FixturesRepository
import by.aderman.tottenhamhotspurfc.domain.repositories.NewsRepository
import by.aderman.tottenhamhotspurfc.domain.repositories.SeasonRepository
import by.aderman.tottenhamhotspurfc.domain.repositories.TeamRepository
import by.aderman.tottenhamhotspurfc.domain.usecases.fixtures.*
import by.aderman.tottenhamhotspurfc.domain.usecases.news.DeleteArticleUseCase
import by.aderman.tottenhamhotspurfc.domain.usecases.news.GetBookmarksUseCase
import by.aderman.tottenhamhotspurfc.domain.usecases.news.GetNewsUseCase
import by.aderman.tottenhamhotspurfc.domain.usecases.news.SaveArticleUseCase
import by.aderman.tottenhamhotspurfc.domain.usecases.season.GetLeagueTableUseCase
import by.aderman.tottenhamhotspurfc.domain.usecases.season.GetTopAssistsUseCase
import by.aderman.tottenhamhotspurfc.domain.usecases.season.GetTopScorersUseCase
import by.aderman.tottenhamhotspurfc.domain.usecases.team.GetPlayerStatisticUseCase
import by.aderman.tottenhamhotspurfc.domain.usecases.team.GetSavedTeamSquadUseCase
import by.aderman.tottenhamhotspurfc.domain.usecases.team.GetTeamSquadUseCase
import by.aderman.tottenhamhotspurfc.domain.usecases.team.SavePlayerUseCase
import by.aderman.tottenhamhotspurfc.presentation.adapters.fixtures.*
import by.aderman.tottenhamhotspurfc.presentation.adapters.news.NewsAdapter
import by.aderman.tottenhamhotspurfc.presentation.adapters.season.AssistsAdapter
import by.aderman.tottenhamhotspurfc.presentation.adapters.season.GoalsAdapter
import by.aderman.tottenhamhotspurfc.presentation.adapters.season.TableAdapter
import by.aderman.tottenhamhotspurfc.presentation.adapters.team.TeamAdapter
import by.aderman.tottenhamhotspurfc.presentation.ui.fragments.matches.*
import by.aderman.tottenhamhotspurfc.presentation.ui.fragments.news.LatestNewsFragment
import by.aderman.tottenhamhotspurfc.presentation.ui.fragments.news.SavedNewsFragment
import by.aderman.tottenhamhotspurfc.presentation.viewmodels.fixtures.FixturesViewModel
import by.aderman.tottenhamhotspurfc.utils.LinearMarginItemDecoration
import by.aderman.tottenhamhotspurfc.presentation.viewmodels.news.NewsViewModel
import by.aderman.tottenhamhotspurfc.presentation.viewmodels.season.SeasonViewModel
import by.aderman.tottenhamhotspurfc.presentation.viewmodels.team.TeamViewModel
import by.aderman.tottenhamhotspurfc.utils.Constants
import by.aderman.tottenhamhotspurfc.utils.GridMarginItemDecoration
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.androidx.fragment.dsl.fragment
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

    fun provideFixtureDao(database: Database): FixtureDao {
        return database.getFixtureDao()
    }

    fun provideResultDao(database: Database): ResultDao {
        return database.getResultDao()
    }

    fun providePlayerDao(database: Database): PlayerDao {
        return database.getPlayerDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideArticleDao(get()) }
    single { provideFixtureDao(get()) }
    single { provideResultDao(get()) }
    single { providePlayerDao(get()) }
}

val apiModules = module {

    single { FootballInterceptor() }

    single {
        OkHttpClient.Builder().apply {
            addInterceptor(get<FootballInterceptor>())
            connectTimeout(Constants.API_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            readTimeout(Constants.API_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
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
    factory { FixturesResponseMapper() }
    factory { FixtureInfoResponseMapper() }
    factory { FixturesLocalMapper() }
    factory { ResultsLocalMapper() }
    factory { PlayerLocalMapper() }

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

    single<TeamLocalDataSource> {
        TeamLocalDataSourceImpl(
            playerDao = get(),
            mapper = get()
        )
    }

    single<TeamRepository> {
        TeamRepositoryImpl(
            remoteDataSource = get(),
            localDataSource = get()
        )
    }

    single<SeasonRemoteDataSource> {
        SeasonRemoteDataSourceImpl(
            api = get(),
            standingsResponseMapper = get(),
            topScorersResponseMapper = get(),
            topAssistsResponseMapper = get()
        )
    }

    single<SeasonRepository> { SeasonRepositoryImpl(get()) }

    single<FixturesRemoteDataSource> {
        FixturesRemoteDataSourceImpl(
            api = get(),
            fixturesResponseMapper = get(),
            fixtureInfoResponseMapper = get()
        )
    }

    single<FixturesLocalDataSource> {
        FixturesLocalDataSourceImpl(
            fixtureDao = get(),
            mapper = get()
        )
    }

    single<ResultsLocalDataSource> {
        ResultsLocalDataSourceImpl(
            resultDao = get(),
            mapper = get()
        )
    }

    single<FixturesRepository> {
        FixturesRepositoryImpl(
            remoteDataSource = get(),
            fixturesLocalDataSource = get(),
            resultsLocalDataSource = get()
        )
    }
}

val applicationModules = module {

    factory { NewsAdapter() }
    factory { TeamAdapter() }
    factory { TableAdapter() }
    factory { GoalsAdapter() }
    factory { AssistsAdapter() }
    factory { FixturesAdapter() }
    factory { HomeLineupAdapter() }
    factory { AwayLineupAdapter() }
    factory { EventsAdapter() }
    factory { LinearMarginItemDecoration() }
    factory { GridMarginItemDecoration() }

    factory { DeleteArticleUseCase(get()) }
    factory { GetBookmarksUseCase(get()) }
    factory { GetNewsUseCase(get()) }
    factory { SaveArticleUseCase(get()) }

    factory { GetPlayerStatisticUseCase(get()) }
    factory { GetTeamSquadUseCase(get()) }

    factory { GetLeagueTableUseCase(get()) }
    factory { GetTopScorersUseCase(get()) }
    factory { GetTopAssistsUseCase(get()) }

    factory { GetFixturesUseCase(get()) }
    factory { GetResultsUseCase(get()) }
    factory { GetFixtureInfoUseCase(get()) }
    factory { SaveFixturesUseCase(get()) }
    factory { GetSavedFixturesUseCase(get()) }
    factory { DeleteOldFixturesUseCase(get()) }
    factory { GetFixtureForAlarmUseCase(get()) }
    factory { UpdateFixtureUseCase(get()) }
    factory { GetSavedResultsUseCase(get()) }
    factory { SaveResultUseCase(get()) }
    factory { GetSavedTeamSquadUseCase(get()) }
    factory { SavePlayerUseCase(get()) }

    fragment { FixturesFragment() }
    fragment { ResultsFragment() }
    fragment { EventsFragment() }
    fragment { StatsFragment() }
    fragment { LineupsFragment() }
    fragment { LatestNewsFragment() }
    fragment { SavedNewsFragment() }

    fun provideSharedPreferences(app: Application): SharedPreferences =
        app.getSharedPreferences(Constants.PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)

    single { provideSharedPreferences(androidApplication()) }

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
            getSavedTeamSquadUseCase = get(),
            savePlayerUseCase = get(),
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

    viewModel {
        FixturesViewModel(
            getFixturesUseCase = get(),
            getResultsUseCase = get(),
            getFixtureInfoUseCase = get(),
            saveFixturesUseCase = get(),
            deleteOldFixturesUseCase = get(),
            getSavedFixturesUseCase = get(),
            updateFixtureUseCase = get(),
            getSavedResultsUseCase = get(),
            saveResultUseCase = get(),
            application = androidApplication()
        )
    }
}