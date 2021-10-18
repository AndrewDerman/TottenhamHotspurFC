package by.aderman.tottenhamhotspurfc.data.repositories.team

import by.aderman.tottenhamhotspurfc.data.api.football.FootballApi
import by.aderman.tottenhamhotspurfc.data.mappers.team.PlayerResponseMapper
import by.aderman.tottenhamhotspurfc.data.mappers.team.TeamResponseMapper
import by.aderman.tottenhamhotspurfc.domain.common.Result
import by.aderman.tottenhamhotspurfc.domain.models.team.Player
import by.aderman.tottenhamhotspurfc.domain.models.team.PlayerWithStats

class TeamRemoteDataSourceImpl(
    private val api: FootballApi,
    private val teamResponseMapper: TeamResponseMapper,
    private val playerResponseMapper: PlayerResponseMapper
) : TeamRemoteDataSource {

    override suspend fun getTeamSquad(): Result<List<Player>> {
        val response = api.getTeamSquad()
        return if (response.isSuccessful) {
            Result.Success(response.body()?.let { teamResponseMapper.toPlayerList(it) })
        } else Result.Error(response.message())
    }

    override suspend fun getPlayerStatistic(playerId: Int): Result<PlayerWithStats> {
        val response = api.getPlayerStatistic(playerId)
        return if (response.isSuccessful) {
            Result.Success(response.body()?.let { playerResponseMapper.toPlayerWithStats(it) })
        } else Result.Error(response.message())
    }
}