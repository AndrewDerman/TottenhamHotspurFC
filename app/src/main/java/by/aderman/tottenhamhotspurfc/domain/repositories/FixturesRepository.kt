package by.aderman.tottenhamhotspurfc.domain.repositories

import by.aderman.tottenhamhotspurfc.data.dto.fixtures.FixtureLocal
import by.aderman.tottenhamhotspurfc.domain.common.Result
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.Fixture
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.FixtureInfo

interface FixturesRepository {

    suspend fun getFixtures(fromDate: String): Result<List<Fixture>>
    suspend fun getResults(toDate: String): Result<List<Fixture>>
    suspend fun getFixtureInfo(fixtureId: Int): Result<FixtureInfo>
    suspend fun saveFixtures(fixturesList: List<Fixture>)
    suspend fun deleteOldFixtures(timestamp: Int)
    suspend fun getFixtureForAlarm(fixtureId: Int): FixtureLocal
    suspend fun getSavedFixtures(): List<FixtureLocal>
    suspend fun updateFixture(fixture: FixtureLocal)
    suspend fun getSavedResults() : List<Fixture>
    suspend fun saveResult(fixture: Fixture)
}