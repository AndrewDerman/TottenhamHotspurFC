package by.aderman.tottenhamhotspurfc.domain.usecases.fixtures

import by.aderman.tottenhamhotspurfc.domain.models.fixtures.Fixture
import by.aderman.tottenhamhotspurfc.domain.repositories.FixturesRepository

class SaveFixturesUseCase(private val repository: FixturesRepository) {
    suspend operator fun invoke(fixturesList: List<Fixture>) =
        repository.saveFixtures(fixturesList)
}