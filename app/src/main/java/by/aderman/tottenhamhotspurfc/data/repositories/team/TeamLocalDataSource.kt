package by.aderman.tottenhamhotspurfc.data.repositories.team

import by.aderman.tottenhamhotspurfc.domain.models.team.Player

interface TeamLocalDataSource {

    suspend fun getSavedTeamSquad(): List<Player>
    suspend fun savePlayer(player: Player)
}