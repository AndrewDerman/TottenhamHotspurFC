package by.aderman.tottenhamhotspurfc.data.mappers.team

import by.aderman.tottenhamhotspurfc.data.dto.team.TeamResponse
import by.aderman.tottenhamhotspurfc.domain.models.team.Player

class TeamResponseMapper {

    fun toPlayerList(response: TeamResponse): List<Player>? {
        return response.response?.get(0)?.players?.map {
            Player(
                id = it.id ?: -1,
                name = it.name.orEmpty(),
                age = it.age ?: -1,
                number = it.number ?: -1,
                position = it.position.orEmpty(),
                photo = it.photo.orEmpty()
            )
        }
    }
}