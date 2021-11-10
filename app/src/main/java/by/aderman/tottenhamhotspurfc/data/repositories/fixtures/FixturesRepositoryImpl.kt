package by.aderman.tottenhamhotspurfc.data.repositories.fixtures

import by.aderman.tottenhamhotspurfc.data.dto.fixtures.FixtureLocal
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.Fixture
import by.aderman.tottenhamhotspurfc.domain.repositories.FixturesRepository
import by.aderman.tottenhamhotspurfc.domain.common.Result
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.FixtureInfo

class FixturesRepositoryImpl(
    private val remoteDataSource: FixturesRemoteDataSource,
    private val fixturesLocalDataSource: FixturesLocalDataSource,
    private val resultsLocalDataSource: ResultsLocalDataSource
) :
    FixturesRepository {

    override suspend fun getFixtures(fromDate: String): Result<List<Fixture>> =
        remoteDataSource.getFixtures(fromDate)

    override suspend fun getResults(toDate: String): Result<List<Fixture>> =
        remoteDataSource.getResults(toDate)

    override suspend fun getFixtureInfo(fixtureId: Int): Result<FixtureInfo> =
        remoteDataSource.getFixtureInfo(fixtureId)

    override suspend fun saveFixtures(fixturesList: List<Fixture>) =
        fixturesLocalDataSource.saveFixtures(fixturesList)

    override suspend fun deleteOldFixtures(timestamp: Int) =
        fixturesLocalDataSource.deleteOldFixtures(timestamp)

    override suspend fun getFixtureForAlarm(fixtureId: Int): FixtureLocal =
        fixturesLocalDataSource.getFixtureForAlarm(fixtureId)

    override suspend fun getSavedFixtures(): List<FixtureLocal> =
        fixturesLocalDataSource.getSavedFixtures()

    override suspend fun updateFixture(fixture: FixtureLocal) =
        fixturesLocalDataSource.updateFixture(fixture)

    override suspend fun getSavedResults(): List<Fixture> = resultsLocalDataSource.getSavedResults()

    override suspend fun saveResult(fixture: Fixture) = resultsLocalDataSource.saveResult(fixture)
}
