package by.aderman.tottenhamhotspurfc.data.mappers.fixtures

import by.aderman.tottenhamhotspurfc.data.dto.fixtureinfo.FixtureInfoResponse
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.*
import by.aderman.tottenhamhotspurfc.utils.Constants
import by.aderman.tottenhamhotspurfc.utils.toLocalTime

class FixtureInfoResponseMapper {

    fun toFixtureInfo(response: FixtureInfoResponse): FixtureInfo {
        val resp = response.response?.get(0)
        val fixture = resp?.fixture
        val league = resp?.league
        val teams = resp?.teams
        val goals = resp?.goals
        val score = resp?.score
        val events = resp?.events
        val lineups = resp?.lineups
        val stats = resp?.statistics
        var homeStatsMap: Map<String?, Any?>? = null
        var awayStatsMap: Map<String?, Any?>? = null

        if (!stats.isNullOrEmpty()) {
            homeStatsMap = stats[0].statistics?.map { it.type to it.value }?.toMap()
            awayStatsMap = stats[1].statistics?.map { it.type to it.value }?.toMap()
        }

        return FixtureInfo(
            id = fixture?.id ?: -1,
            status = Status(
                elapsed = fixture?.status?.elapsed,
                longValue = fixture?.status?.long.orEmpty(),
                shortValue = fixture?.status?.short.orEmpty()
            ),
            date = toLocalTime(
                fixture?.date,
                Constants.FOOTBALL_API_INPUT_TIME_FORMAT
            ).orEmpty(),
            periods = Periods(
                first = fixture?.periods?.first ?: -1,
                second = fixture?.periods?.second ?: -1
            ),
            referee = fixture?.referee,
            timestamp = fixture?.timestamp ?: -1,
            timezone = fixture?.timezone ?: Constants.TIMEZONE_UTC,
            venue = Venue(
                id = fixture?.venue?.id ?: -1,
                city = fixture?.venue?.city.orEmpty(),
                name = fixture?.venue?.name.orEmpty()
            ),
            goals = Goals(
                away = goals?.away ?: 0,
                home = goals?.home ?: 0
            ),
            league = League(
                id = league?.id ?: -1,
                country = league?.country ?: Constants.FOOTBALL_COUNTRY_NAME,
                flag = league?.flag.orEmpty(),
                logo = league?.logo.orEmpty(),
                name = league?.name ?: Constants.FOOTBALL_LEAGUE_NAME,
                season = league?.season ?: Constants.FOOTBALL_CURRENT_SEASON,
                round = "Round ${league?.round?.substring(17)}"
            ),
            score = Score(
                extratime = Extratime(
                    away = score?.extratime?.away ?: 0,
                    home = score?.extratime?.home ?: 0
                ),
                fulltime = Fulltime(
                    away = score?.fulltime?.away ?: 0,
                    home = score?.fulltime?.home ?: 0
                ),
                halftime = Halftime(
                    away = score?.halftime?.away ?: 0,
                    home = score?.halftime?.home ?: 0
                ),
                penalty = Penalty(
                    away = score?.penalty?.away,
                    home = score?.penalty?.home
                )
            ),
            teams = Teams(
                away = Away(
                    id = teams?.away?.id ?: -1,
                    logo = teams?.away?.logo.orEmpty(),
                    name = teams?.away?.name.orEmpty(),
                    winner = teams?.away?.winner ?: false
                ),
                home = Home(
                    id = teams?.home?.id ?: -1,
                    logo = teams?.home?.logo.orEmpty(),
                    name = teams?.home?.name.orEmpty(),
                    winner = teams?.home?.winner ?: false
                )
            ),
            events = events?.map {
                Event(
                    time = EventTime(
                        elapsed = it.time?.elapsed ?: -1,
                        extra = it.time?.extra
                    ),
                    type = it.type.orEmpty(),
                    detail = it.detail.orEmpty(),
                    comments = it.comments,
                    team = EventTeam(
                        id = it.team?.id ?: -1,
                        name = it.team?.name.orEmpty(),
                        logo = it.team?.logo.orEmpty(),
                        isHome = it.team?.id == teams?.home?.id
                    ),
                    player = EventPlayer(
                        id = it.player?.id ?: -1,
                        name = it.player?.name.orEmpty()
                    ),
                    assist = EventAssistant(
                        id = it.assist?.id,
                        name = it.assist?.name
                    )
                )
            },
            lineups = lineups?.map {
                Lineup(
                    team = Team(
                        id = it.team?.id ?: -1,
                        name = it.team?.name.orEmpty(),
                        logo = it.team?.logo.orEmpty()
                    ),
                    coach = Coach(
                        id = it.coach?.id ?: -1,
                        name = it.coach?.name.orEmpty(),
                        photo = it.coach?.photo.orEmpty()
                    ),
                    formation = it.formation,
                    startXI = it.startXI?.map { startXI ->
                        LineupPlayer(
                            id = startXI.player?.id,
                            name = startXI.player?.name,
                            number = startXI.player?.number,
                            pos = startXI.player?.pos,
                            grid = startXI.player?.grid,
                        )
                    },
                    substitutes = it.substitutes?.map { substitute ->
                        LineupPlayer(
                            id = substitute.player?.id,
                            name = substitute.player?.name,
                            number = substitute.player?.number,
                            pos = substitute.player?.pos,
                            grid = substitute.player?.grid,
                        )
                    }
                )
            },
//            statistics = statistics?.map
//            {
//                Statistic(
//                    team = Team(
//                        id = it.team?.id ?: -1,
//                        name = it.team?.name.orEmpty(),
//                        logo = it.team?.logo.orEmpty()
//                    ),
//                    statistics = it.statistics?.map { stats ->
//                        FixtureStats(
//                            type = stats.type.orEmpty(),
//                            value = stats.value.toString()
//                        )
//                    }
//                )
//            }

            statistics = homeStatsMap?.map { homeStats ->
                Statistics(
                    type = FixtureStatsTypes.values().first {
                        it.value == homeStats.key.orEmpty()
                    },
                    homeValue = homeStats.value.toString(),
                    awayValue = awayStatsMap?.get(homeStats.key).toString()
                )
            }?.toList()
        )
    }
}