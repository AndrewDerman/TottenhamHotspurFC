package by.aderman.tottenhamhotspurfc.domain.usecases.fixtures

import by.aderman.tottenhamhotspurfc.data.dto.fixtures.FixtureLocal
import by.aderman.tottenhamhotspurfc.domain.repositories.FixturesRepository

class UpdateFixtureUseCase(private val repository: FixturesRepository) {
    suspend operator fun invoke(fixture: FixtureLocal) = repository.updateFixture(fixture)
}