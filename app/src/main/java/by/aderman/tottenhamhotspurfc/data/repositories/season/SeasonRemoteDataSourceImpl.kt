package by.aderman.tottenhamhotspurfc.data.repositories.season

import by.aderman.tottenhamhotspurfc.data.api.football.FootballApi
import by.aderman.tottenhamhotspurfc.data.mappers.season.StandingsResponseMapper
import by.aderman.tottenhamhotspurfc.data.mappers.season.TopAssistsResponseMapper
import by.aderman.tottenhamhotspurfc.data.mappers.season.TopScorersResponseMapper
import by.aderman.tottenhamhotspurfc.domain.models.season.Standing
import by.aderman.tottenhamhotspurfc.domain.common.Result
import by.aderman.tottenhamhotspurfc.domain.models.season.PlayerTopAssistant
import by.aderman.tottenhamhotspurfc.domain.models.season.PlayerTopScorer

class SeasonRemoteDataSourceImpl(
    private val api: FootballApi,
    private val standingsResponseMapper: StandingsResponseMapper,
    private val topScorersResponseMapper: TopScorersResponseMapper,
    private val topAssistsResponseMapper: TopAssistsResponseMapper
) : SeasonRemoteDataSource {

    override suspend fun getLeagueTable(): Result<List<Standing>> {
        val response = api.getLeagueTable()
        return if (response.isSuccessful) {
            Result.Success(response.body()?.let { standingsResponseMapper.toStandingList(it) })
        } else Result.Error(response.message())
    }

    override suspend fun getTopScorers(): Result<List<PlayerTopScorer>> {
        val response = api.getTopScorers()
        return if (response.isSuccessful) {
            Result.Success(response.body()?.let { topScorersResponseMapper.toTopScorersList(it) })
        } else Result.Error(response.message())
    }

    override suspend fun getTopAssists(): Result<List<PlayerTopAssistant>> {
        val response = api.getTopAssists()
        return if (response.isSuccessful) {
            Result.Success(
                response.body()?.let { topAssistsResponseMapper.toTopAssistantsList(it) })
        } else Result.Error(response.message())
    }
}