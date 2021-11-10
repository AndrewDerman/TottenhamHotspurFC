package by.aderman.tottenhamhotspurfc.data.repositories.fixtures

import by.aderman.tottenhamhotspurfc.domain.models.fixtures.Fixture

interface ResultsLocalDataSource {

    suspend fun getSavedResults(): List<Fixture>
    suspend fun saveResult(fixture: Fixture)
}