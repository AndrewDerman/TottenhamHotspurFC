package by.aderman.tottenhamhotspurfc.data.mappers.season

import by.aderman.tottenhamhotspurfc.data.dto.football.responses.StandingsResponse
import by.aderman.tottenhamhotspurfc.domain.models.season.*
import by.aderman.tottenhamhotspurfc.domain.models.team.Team
import by.aderman.tottenhamhotspurfc.utils.Constants
import by.aderman.tottenhamhotspurfc.utils.toLocalTime

class StandingsResponseMapper {

    fun toStandingList(response: StandingsResponse): List<Standing>? {
        return response.response?.get(0)?.league?.standings?.get(0)?.map {
            Standing(
                rank = it.rank ?: -1,
                Team(
                    id = it.team?.id ?: -1,
                    name = it.team?.name.orEmpty(),
                    logo = it.team?.logo.orEmpty()
                ),
                points = it.points ?: -1,
                goalsDiff = it.goalsDiff ?: -1,
                All(
                    played = it.all?.played ?: -1,
                    win = it.all?.win ?: -1,
                    draw = it.all?.draw ?: -1,
                    lose = it.all?.lose ?: -1,
                    Goals(
                        against = it.all?.goals?.against ?: -1,
                        forX = it.all?.goals?.forX ?: -1,
                    )
                ),
                Home(
                    played = it.home?.played ?: -1,
                    win = it.home?.win ?: -1,
                    draw = it.home?.draw ?: -1,
                    lose = it.home?.lose ?: -1,
                    Goals(
                        against = it.home?.goals?.against ?: -1,
                        forX = it.home?.goals?.forX ?: -1,
                    )
                ),
                Away(
                    played = it.away?.played ?: -1,
                    win = it.away?.win ?: -1,
                    draw = it.away?.draw ?: -1,
                    lose = it.away?.lose ?: -1,
                    Goals(
                        against = it.away?.goals?.against ?: -1,
                        forX = it.away?.goals?.forX ?: -1,
                    )
                ),
                form = it.form.orEmpty(),
                update = toLocalTime(it.update, Constants.FOOTBALL_API_INPUT_TIME_FORMAT).orEmpty()
            )
        }
    }
}