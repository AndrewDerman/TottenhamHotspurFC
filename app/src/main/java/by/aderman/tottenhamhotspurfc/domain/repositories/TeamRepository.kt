package by.aderman.tottenhamhotspurfc.domain.repositories

import by.aderman.tottenhamhotspurfc.domain.common.Result
import by.aderman.tottenhamhotspurfc.domain.models.team.Player
import by.aderman.tottenhamhotspurfc.domain.models.team.PlayerWithStats

interface TeamRepository {

    suspend fun getTeamSquad(): Result<List<Player>>

    suspend fun getPlayerStatistic(playerId: Int): Result<PlayerWithStats>
}