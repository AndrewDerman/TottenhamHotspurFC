package by.aderman.tottenhamhotspurfc.data.mappers.fixtures

import by.aderman.tottenhamhotspurfc.data.dto.football.responses.FixturesResponse
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.*
import by.aderman.tottenhamhotspurfc.utils.Constants
import by.aderman.tottenhamhotspurfc.utils.toLocalTime

class FixturesResponseMapper {

    fun toFixturesList(response: FixturesResponse): List<Fixture>? {
        return response.response?.map {
            Fixture(
                id = it.fixture?.id ?: -1,
                status = Status(
                    elapsed = it.fixture?.status?.elapsed,
                    longValue = it.fixture?.status?.long.orEmpty(),
                    shortValue = it.fixture?.status?.short.orEmpty()
                ),
                date = toLocalTime(
                    it.fixture?.date,
                    Constants.FOOTBALL_API_INPUT_TIME_FORMAT
                ).orEmpty(),
                periods = Periods(
                    first = it.fixture?.periods?.first ?: -1,
                    second = it.fixture?.periods?.second ?: -1
                ),
                referee = it.fixture?.referee.orEmpty(),
                timestamp = it.fixture?.timestamp ?: -1,
                timezone = it.fixture?.timezone ?: Constants.TIMEZONE_UTC,
                venue = Venue(
                    id = it.fixture?.venue?.id ?: -1,
                    city = it.fixture?.venue?.city.orEmpty(),
                    name = it.fixture?.venue?.name.orEmpty()
                ),
                goals = Goals(
                    away = it.goals?.away ?: 0,
                    home = it.goals?.home ?: 0
                ),
                league = League(
                    id = it.league?.id ?: Constants.FOOTBALL_LEAGUE_ID,
                    country = it.league?.country ?: Constants.FOOTBALL_COUNTRY_NAME,
                    flag = it.league?.flag.orEmpty(),
                    logo = it.league?.logo.orEmpty(),
                    name = it.league?.name ?: Constants.FOOTBALL_LEAGUE_NAME,
                    season = it.league?.season ?: Constants.FOOTBALL_CURRENT_SEASON,
                    round = it.league?.round.orEmpty()
                ),
                score = Score(
                    extratime = Extratime(
                        away = it.score?.extratime?.away ?: 0,
                        home = it.score?.extratime?.home ?: 0
                    ),
                    fulltime = Fulltime(
                        away = it.score?.fulltime?.away ?: 0,
                        home = it.score?.fulltime?.home ?: 0
                    ),
                    halftime = Halftime(
                        away = it.score?.halftime?.away ?: 0,
                        home = it.score?.halftime?.home ?: 0
                    ),
                    penalty = Penalty(
                        away = it.score?.penalty?.away,
                        home = it.score?.penalty?.home,
                    )
                ),
                teams = Teams(
                    away = Away(
                        id = it.teams?.away?.id ?: -1,
                        logo = it.teams?.away?.logo.orEmpty(),
                        name = it.teams?.away?.name.orEmpty(),
                        winner = it.teams?.away?.winner ?: false
                    ),
                    home = Home(
                        id = it.teams?.home?.id ?: -1,
                        logo = it.teams?.home?.logo.orEmpty(),
                        name = it.teams?.home?.name.orEmpty(),
                        winner = it.teams?.home?.winner ?: false
                    )
                )
            )
        }
    }
}