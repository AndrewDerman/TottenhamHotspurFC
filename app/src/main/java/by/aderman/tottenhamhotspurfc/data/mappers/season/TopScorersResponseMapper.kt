package by.aderman.tottenhamhotspurfc.data.mappers.season

import by.aderman.tottenhamhotspurfc.data.dto.topscorers.TopScorersResponse
import by.aderman.tottenhamhotspurfc.domain.models.season.PlayerTopScorer
import by.aderman.tottenhamhotspurfc.domain.models.team.Team

class TopScorersResponseMapper {

    fun toTopScorersList(response: TopScorersResponse): List<PlayerTopScorer>? {
        return response.response?.map {
            PlayerTopScorer(
                rank = response.response.indexOf(it) + 1,
                name = it.player?.name.orEmpty(),
                photo = it.player?.photo.orEmpty(),
                Team(
                    id = it.statistics?.get(0)?.team?.id ?: -1,
                    name = it.statistics?.get(0)?.team?.name.orEmpty(),
                    logo = it.statistics?.get(0)?.team?.logo.orEmpty(),
                ),
                goals = it.statistics?.get(0)?.goals?.total ?: 0,
                penalties = it.statistics?.get(0)?.penalty?.scored ?: 0
            )
        }
    }
}