package by.aderman.tottenhamhotspurfc.domain.usecases.fixtures

import by.aderman.tottenhamhotspurfc.domain.repositories.FixturesRepository

class GetSavedFixturesUseCase(private val repository: FixturesRepository) {
    suspend operator fun invoke() = repository.getSavedFixtures()
}