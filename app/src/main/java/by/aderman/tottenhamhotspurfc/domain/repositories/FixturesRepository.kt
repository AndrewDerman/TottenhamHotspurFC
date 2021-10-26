package by.aderman.tottenhamhotspurfc.domain.repositories

import by.aderman.tottenhamhotspurfc.domain.models.fixtures.Fixture
import by.aderman.tottenhamhotspurfc.domain.common.Result
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.FixtureInfo

interface FixturesRepository {
    suspend fun getFixtures(fromDate: String): Result<List<Fixture>>
    suspend fun getResults(toDate: String): Result<List<Fixture>>
    suspend fun getFixtureInfo(fixtureId: Int): Result<FixtureInfo>
}