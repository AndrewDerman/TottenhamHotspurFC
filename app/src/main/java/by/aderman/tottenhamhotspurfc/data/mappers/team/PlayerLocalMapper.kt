package by.aderman.tottenhamhotspurfc.data.mappers.team

import by.aderman.tottenhamhotspurfc.data.dto.team.PlayerLocal
import by.aderman.tottenhamhotspurfc.domain.models.team.Player

class PlayerLocalMapper {

    fun toPlayerLocal(player: Player): PlayerLocal {
        return PlayerLocal(
            id = player.id,
            name = player.name,
            age = player.age,
            number = player.number ?: -1,
            position = player.position,
            photo = player.photo
        )
    }

    fun toPlayer(playerLocal: PlayerLocal): Player {
        return Player(
            id = playerLocal.id,
            name = playerLocal.name,
            age = playerLocal.age,
            number = if (playerLocal.number < 0) null else playerLocal.number,
            position = playerLocal.position,
            photo = playerLocal.photo
        )
    }
}