package by.aderman.tottenhamhotspurfc.data.repositories.team

import by.aderman.tottenhamhotspurfc.data.db.PlayerDao
import by.aderman.tottenhamhotspurfc.data.mappers.team.PlayerLocalMapper
import by.aderman.tottenhamhotspurfc.domain.models.team.Player

class TeamLocalDataSourceImpl(
    private val playerDao: PlayerDao,
    private val mapper: PlayerLocalMapper
) : TeamLocalDataSource {

    override suspend fun getSavedTeamSquad(): List<Player> = playerDao.getSavedTeamSquad().map {
        mapper.toPlayer(it)
    }

    override suspend fun savePlayer(player: Player) =
        playerDao.savePlayer(mapper.toPlayerLocal(player))
}