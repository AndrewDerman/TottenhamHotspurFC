package by.aderman.tottenhamhotspurfc.data.mappers.fixtures

import by.aderman.tottenhamhotspurfc.data.dto.fixtures.FixtureLocal
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.Fixture

class FixturesLocalMapper {

    fun toFixtureLocal(fixture: Fixture): FixtureLocal {
        return FixtureLocal(
            id = fixture.id,
            date = fixture.date,
            timestamp = fixture.timestamp,
            timezone = fixture.timezone,
            venueName = fixture.venue.name,
            homeTeamName = fixture.teams.home.name,
            awayTeamName = fixture.teams.away.name
        )
    }
}