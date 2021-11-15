package by.aderman.tottenhamhotspurfc.data.repositories.fixtures

import by.aderman.tottenhamhotspurfc.data.api.football.FootballApi
import by.aderman.tottenhamhotspurfc.data.mappers.fixtures.FixtureInfoResponseMapper
import by.aderman.tottenhamhotspurfc.data.mappers.fixtures.FixturesResponseMapper
import by.aderman.tottenhamhotspurfc.domain.common.Result
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.Fixture
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.FixtureInfo

class FixturesRemoteDataSourceImpl(
    private val api: FootballApi,
    private val fixturesResponseMapper: FixturesResponseMapper,
    private val fixtureInfoResponseMapper: FixtureInfoResponseMapper
) : FixturesRemoteDataSource {

    override suspend fun getFixtures(fromDate: String): Result<List<Fixture>> {
        val response = api.getFixtures(fromDate = fromDate)
        return if (response.isSuccessful) {
            Result.Success(response.body()?.let { fixturesResponseMapper.toFixturesList(it) })
        } else Result.Error(response.message())
    }

    override suspend fun getResults(toDate: String): Result<List<Fixture>> {
        val response = api.getResults(toDate = toDate)
        return if (response.isSuccessful) {
            Result.Success(response.body()?.let { fixturesResponseMapper.toFixturesList(it) })
        } else Result.Error(response.message())
    }

    override suspend fun getFixtureInfo(fixtureId: Int): Result<FixtureInfo> {
        val response = api.getFixtureInfo(fixtureId)
        return if (response.isSuccessful) {
            Result.Success(response.body()?.let { fixtureInfoResponseMapper.toFixtureInfo(it) })
        } else Result.Error(response.message())
    }
}