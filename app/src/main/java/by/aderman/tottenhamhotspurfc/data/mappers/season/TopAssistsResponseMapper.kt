package by.aderman.tottenhamhotspurfc.data.mappers.season

import by.aderman.tottenhamhotspurfc.data.dto.topassists.TopAssistsResponse
import by.aderman.tottenhamhotspurfc.domain.models.season.PlayerTopAssistant
import by.aderman.tottenhamhotspurfc.domain.models.team.Team

class TopAssistsResponseMapper {

    fun toTopAssistantsList(response: TopAssistsResponse): List<PlayerTopAssistant>? {
        return response.response?.map {
            PlayerTopAssistant(
                rank = response.response.indexOf(it) + 1,
                name = it.player?.name.orEmpty(),
                photo = it.player?.photo.orEmpty(),
                Team(
                    id = it.statistics?.get(0)?.team?.id ?: -1,
                    name = it.statistics?.get(0)?.team?.name.orEmpty(),
                    logo = it.statistics?.get(0)?.team?.logo.orEmpty(),
                ),
                assists = it.statistics?.get(0)?.goals?.assists ?: -1
            )
        }
    }
}