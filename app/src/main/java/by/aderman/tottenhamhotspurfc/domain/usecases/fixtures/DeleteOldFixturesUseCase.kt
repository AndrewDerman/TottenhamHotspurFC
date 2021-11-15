package by.aderman.tottenhamhotspurfc.domain.usecases.fixtures

import by.aderman.tottenhamhotspurfc.domain.repositories.FixturesRepository

class DeleteOldFixturesUseCase(private val repository: FixturesRepository) {
    suspend operator fun invoke(timestamp: Int) = repository.deleteOldFixtures(timestamp)
}