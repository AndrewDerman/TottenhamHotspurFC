package by.aderman.tottenhamhotspurfc.data.repositories.fixtures

import by.aderman.tottenhamhotspurfc.data.db.FixtureDao
import by.aderman.tottenhamhotspurfc.data.dto.fixtures.FixtureLocal
import by.aderman.tottenhamhotspurfc.data.mappers.fixtures.FixturesLocalMapper
import by.aderman.tottenhamhotspurfc.domain.models.fixtures.Fixture

class FixturesLocalDataSourceImpl(
    private val fixtureDao: FixtureDao,
    private val mapper: FixturesLocalMapper
) : FixturesLocalDataSource {

    override suspend fun getSavedFixtures(): List<FixtureLocal> =
        fixtureDao.getSavedFixtures()

    override suspend fun getFixtureForAlarm(fixtureId: Int): FixtureLocal =
        fixtureDao.getFixtureForAlarm(fixtureId)

    override suspend fun saveFixtures(fixturesList: List<Fixture>) {
        if (!fixturesList.isNullOrEmpty()) {
            for (fixture in fixturesList) {
                fixtureDao.saveFixture(mapper.toFixtureLocal(fixture))
            }
        }
    }

    override suspend fun deleteOldFixtures(timestamp: Int) = fixtureDao.deleteOldFixtures(timestamp)

    override suspend fun updateFixture(fixtureLocal: FixtureLocal) =
        fixtureDao.updateFixture(fixtureLocal)
}