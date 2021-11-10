package by.aderman.tottenhamhotspurfc.data.repositories.fixtures

import by.aderman.tottenhamhotspurfc.data.db.ResultDao
import by.aderman.tottenhamhotspurfc.data.mappers.fixtures.ResultsLocalMapper
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.Fixture

class ResultsLocalDataSourceImpl(
    private val resultDao: ResultDao,
    private val mapper: ResultsLocalMapper
) : ResultsLocalDataSource {

    override suspend fun getSavedResults(): List<Fixture> {
        return resultDao.getSavedResults().map {
            mapper.toFixture(it)
        }
    }

    override suspend fun saveResult(fixture: Fixture) {
        resultDao.saveResult(mapper.toResultLocal(fixture))
    }
}