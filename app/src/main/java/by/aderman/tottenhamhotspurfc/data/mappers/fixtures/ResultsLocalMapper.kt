package by.aderman.tottenhamhotspurfc.data.mappers.fixtures

import by.aderman.tottenhamhotspurfc.data.dto.fixtures.ResultLocal
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.*

class ResultsLocalMapper {

    fun toResultLocal(fixture: Fixture): ResultLocal {
        return ResultLocal(
            id = fixture.id,
            elapsedTime = fixture.status.elapsed ?: 90,
            statusLongValue = fixture.status.longValue,
            statusShortValue = fixture.status.shortValue,
            date = fixture.date,
            firstPeriod = fixture.periods.first,
            secondPeriod = fixture.periods.second,
            referee = fixture.referee,
            timestamp = fixture.timestamp,
            timezone = fixture.timezone,
            venueId = fixture.venue.id,
            venueCity = fixture.venue.city,
            venueName = fixture.venue.name,
            goalsAway = fixture.goals.away,
            goalsHome = fixture.goals.home,
            leagueId = fixture.league.id,
            leagueCountry = fixture.league.country,
            leagueFlag = fixture.league.flag,
            leagueLogo = fixture.league.logo,
            leagueName = fixture.league.name,
            leagueSeason = fixture.league.season,
            leagueRound = fixture.league.round,
            scoreExtratimeAway = fixture.score.extratime.away,
            scoreExtratimeHome = fixture.score.extratime.home,
            scoreFulltimeAway = fixture.score.fulltime.away,
            scoreFulltimeHome = fixture.score.fulltime.home,
            scoreHalftimeAway = fixture.score.halftime.away,
            scoreHalftimeHome = fixture.score.halftime.home,
            scorePenaltyAway = fixture.score.penalty.away ?: -1,
            scorePenaltyHome = fixture.score.penalty.home ?: -1,
            teamAwayId = fixture.teams.away.id,
            teamAwayLogo = fixture.teams.away.logo,
            teamAwayName = fixture.teams.away.name,
            teamAwayWinner = fixture.teams.away.winner,
            teamHomeId = fixture.teams.home.id,
            teamHomeLogo = fixture.teams.home.logo,
            teamHomeName = fixture.teams.home.name,
            teamHomeWinner = fixture.teams.home.winner
        )
    }

    fun toFixture(result: ResultLocal): Fixture {
        return Fixture(
            id = result.id,
            status = Status(
                elapsed = result.elapsedTime,
                longValue = result.statusLongValue,
                shortValue = result.statusShortValue,
            ),
            date = result.date,
            periods = Periods(
                first = result.firstPeriod,
                second = result.secondPeriod
            ),
            referee = result.referee,
            timestamp = result.timestamp,
            timezone = result.timezone,
            venue = Venue(
                id = result.venueId,
                city = result.venueCity,
                name = result.venueName
            ),
            goals = Goals(
                away = result.goalsAway,
                home = result.goalsHome
            ),
            league = League(
                id = result.leagueId,
                country = result.leagueCountry,
                flag = result.leagueFlag,
                logo = result.leagueLogo,
                name = result.leagueName,
                season = result.leagueSeason,
                round = result.leagueRound
            ),
            score = Score(
                extratime = Extratime(
                    away = result.scoreExtratimeAway,
                    home = result.scoreExtratimeHome
                ),
                fulltime = Fulltime(
                    away = result.scoreFulltimeAway,
                    home = result.scoreFulltimeHome
                ),
                halftime = Halftime(
                    away = result.scoreHalftimeAway,
                    home = result.scoreHalftimeHome
                ),
                penalty = Penalty(
                    away = result.scorePenaltyAway,
                    home = result.scorePenaltyHome
                )
            ),
            teams = Teams(
                away = Away(
                    id = result.teamAwayId,
                    logo = result.teamAwayLogo,
                    name = result.teamAwayName,
                    winner = result.teamAwayWinner
                ),
                home = Home(
                    id = result.teamHomeId,
                    logo = result.teamHomeLogo,
                    name = result.teamHomeName,
                    winner = result.teamHomeWinner
                )
            )
        )
    }
}