package by.aderman.tottenhamhotspurfc.data.repositories.fixtures

import by.aderman.tottenhamhotspurfc.domain.models.fixtures.Fixture
import by.aderman.tottenhamhotspurfc.domain.repositories.FixturesRepository
import by.aderman.tottenhamhotspurfc.domain.common.Result
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.FixtureInfo

class FixturesRepositoryImpl(private val remoteDataSource: FixturesRemoteDataSource) :
    FixturesRepository {

    override suspend fun getFixtures(fromDate: String): Result<List<Fixture>> =
        remoteDataSource.getFixtures(fromDate)

    override suspend fun getResults(toDate: String): Result<List<Fixture>> =
        remoteDataSource.getResults(toDate)

    override suspend fun getFixtureInfo(fixtureId: Int): Result<FixtureInfo> =
        remoteDataSource.getFixtureInfo(fixtureId)
}