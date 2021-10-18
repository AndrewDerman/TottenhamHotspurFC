package by.aderman.tottenhamhotspurfc.data.repositories.team

import by.aderman.tottenhamhotspurfc.domain.common.Result
import by.aderman.tottenhamhotspurfc.domain.models.team.Player
import by.aderman.tottenhamhotspurfc.domain.models.team.PlayerWithStats
import by.aderman.tottenhamhotspurfc.domain.repositories.TeamRepository

class TeamRepositoryImpl(private val remoteDataSource: TeamRemoteDataSource) : TeamRepository {

    override suspend fun getTeamSquad(): Result<List<Player>> = remoteDataSource.getTeamSquad()

    override suspend fun getPlayerStatistic(playerId: Int): Result<PlayerWithStats> =
        remoteDataSource.getPlayerStatistic(playerId)
}