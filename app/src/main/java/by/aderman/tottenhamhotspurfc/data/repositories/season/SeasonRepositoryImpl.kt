package by.aderman.tottenhamhotspurfc.data.repositories.season

import by.aderman.tottenhamhotspurfc.domain.models.season.Standing
import by.aderman.tottenhamhotspurfc.domain.repositories.SeasonRepository
import by.aderman.tottenhamhotspurfc.domain.common.Result
import by.aderman.tottenhamhotspurfc.domain.models.season.PlayerTopAssistant
import by.aderman.tottenhamhotspurfc.domain.models.season.PlayerTopScorer

class SeasonRepositoryImpl(private val remoteDataSource: SeasonRemoteDataSource) :
    SeasonRepository {

    override suspend fun getLeagueTable(): Result<List<Standing>> =
        remoteDataSource.getLeagueTable()

    override suspend fun getTopScorers(): Result<List<PlayerTopScorer>> =
        remoteDataSource.getTopScorers()

    override suspend fun getTopAssists(): Result<List<PlayerTopAssistant>> =
        remoteDataSource.getTopAssists()
}