package by.aderman.tottenhamhotspurfc.data.repositories.fixtures

import by.aderman.tottenhamhotspurfc.domain.common.Result
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.Fixture
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.FixtureInfo

interface FixturesRemoteDataSource {

    suspend fun getFixtures(fromDate: String): Result<List<Fixture>>
    suspend fun getResults(toDate: String): Result<List<Fixture>>
    suspend fun getFixtureInfo(fixtureId: Int): Result<FixtureInfo>
}