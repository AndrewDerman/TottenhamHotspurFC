package by.aderman.tottenhamhotspurfc.data.mappers.team

import by.aderman.tottenhamhotspurfc.data.dto.player.PlayerResponse
import by.aderman.tottenhamhotspurfc.domain.models.team.*

class PlayerResponseMapper {

    fun toPlayerWithStats(response: PlayerResponse): PlayerWithStats {
        val player = response.response?.get(0)?.player
        val stats = response.response?.get(0)?.statistics?.get(0)
        return PlayerWithStats(
            id = player?.id ?: -1,
            name = player?.name.orEmpty(),
            firstname = player?.firstname.orEmpty(),
            lastname = player?.lastname.orEmpty(),
            age = player?.age ?: -1,
            birth = Birth(
                country = player?.birth?.country.orEmpty(),
                date = player?.birth?.date.orEmpty(),
                place = player?.birth?.place.orEmpty()
            ),
            nationality = player?.nationality.orEmpty(),
            height = player?.height.orEmpty(),
            weight = player?.weight.orEmpty(),
            injured = player?.injured ?: false,
            photo = player?.photo.orEmpty(),
            statistic = Statistic(
                cards = Cards(
                    red = stats?.cards?.red ?: 0,
                    yellow = stats?.cards?.yellow ?: 0,
                    yellowred = stats?.cards?.yellowred ?: 0
                ),
                dribbles = Dribbles(
                    attempts = stats?.dribbles?.attempts ?: 0,
                    past = stats?.dribbles?.past ?: 0,
                    success = stats?.dribbles?.success ?: 0
                ),
                duels = Duels(
                    total = stats?.duels?.total ?: 0,
                    won = stats?.duels?.won ?: 0,
                ),
                fouls = Fouls(
                    committed = stats?.fouls?.committed ?: 0,
                    drawn = stats?.fouls?.drawn ?: 0
                ),
                games = Games(
                    appearences = stats?.games?.appearences ?: 0,
                    captain = stats?.games?.captain ?: false,
                    lineups = stats?.games?.lineups ?: 0,
                    minutes = stats?.games?.minutes ?: 0,
                    number = stats?.games?.number ?: -1,
                    position = stats?.games?.position.orEmpty(),
                    rating = stats?.games?.rating?.substring(0, 4) ?: "0"
                ),
                goals = Goals(
                    assists = stats?.goals?.assists ?: 0,
                    conceded = stats?.goals?.conceded ?: 0,
                    saves = stats?.goals?.saves ?: 0,
                    total = stats?.goals?.total ?: 0
                ),
                league = League(
                    country = stats?.league?.country.orEmpty(),
                    flag = stats?.league?.flag.orEmpty(),
                    id = stats?.league?.id ?: -1,
                    logo = stats?.league?.logo.orEmpty(),
                    name = stats?.league?.name.orEmpty(),
                    season = stats?.league?.season ?: -1
                ),
                passes = Passes(
                    accuracy = stats?.passes?.accuracy ?: 0,
                    key = stats?.passes?.key ?: 0,
                    total = stats?.passes?.total ?: 0
                ),
                penalty = Penalty(
                    commited = stats?.penalty?.commited ?: 0,
                    missed = stats?.penalty?.missed ?: 0,
                    saved = stats?.penalty?.saved ?: 0,
                    scored = stats?.penalty?.scored ?: 0,
                    won = stats?.penalty?.won ?: 0
                ),
                shots = Shots(
                    on = stats?.shots?.on ?: 0,
                    total = stats?.shots?.total ?: 0
                ),
                substitutes = Substitutes(
                    bench = stats?.substitutes?.bench ?: 0,
                    inX = stats?.substitutes?.inX ?: 0,
                    out = stats?.substitutes?.out ?: 0
                ),
                tackles = Tackles(
                    blocks = stats?.tackles?.blocks ?: 0,
                    interceptions = stats?.tackles?.interceptions ?: 0,
                    total = stats?.tackles?.total ?: 0
                ),
                team = Team(
                    id = stats?.team?.id ?: -1,
                    name = stats?.team?.name.orEmpty(),
                    logo = stats?.team?.logo.orEmpty()
                )
            )
        )
    }
}