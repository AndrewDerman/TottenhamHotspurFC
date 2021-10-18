package by.aderman.tottenhamhotspurfc.data.repositories.team

import by.aderman.tottenhamhotspurfc.domain.common.Result
import by.aderman.tottenhamhotspurfc.domain.models.team.Player
import by.aderman.tottenhamhotspurfc.domain.models.team.PlayerWithStats

interface TeamRemoteDataSource {

    suspend fun getTeamSquad(): Result<List<Player>>
    suspend fun getPlayerStatistic(playerId: Int): Result<PlayerWithStats>
}