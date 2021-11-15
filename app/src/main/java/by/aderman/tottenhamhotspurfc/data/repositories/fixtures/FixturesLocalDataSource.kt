package by.aderman.tottenhamhotspurfc.data.repositories.fixtures

import by.aderman.tottenhamhotspurfc.data.dto.fixtures.FixtureLocal
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.Fixture

interface FixturesLocalDataSource {

    suspend fun getSavedFixtures(): List<FixtureLocal>
    suspend fun getFixtureForAlarm(fixtureId: Int): FixtureLocal
    suspend fun saveFixtures(fixturesList: List<Fixture>)
    suspend fun deleteOldFixtures(timestamp: Int)
    suspend fun updateFixture(fixtureLocal: FixtureLocal)
}