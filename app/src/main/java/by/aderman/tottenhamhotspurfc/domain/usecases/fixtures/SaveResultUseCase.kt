package by.aderman.tottenhamhotspurfc.domain.usecases.fixtures

import by.aderman.tottenhamhotspurfc.domain.models.fixtures.Fixture
import by.aderman.tottenhamhotspurfc.domain.repositories.FixturesRepository

class SaveResultUseCase(private val repository: FixturesRepository) {
    suspend operator fun invoke(fixture: Fixture) = repository.saveResult(fixture)
}