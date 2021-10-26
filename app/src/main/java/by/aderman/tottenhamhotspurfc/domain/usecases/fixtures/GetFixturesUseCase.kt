package by.aderman.tottenhamhotspurfc.domain.usecases.fixtures

import by.aderman.tottenhamhotspurfc.domain.repositories.FixturesRepository

class GetFixturesUseCase(private val repository: FixturesRepository) {
    suspend operator fun invoke(fromDate: String) = repository.getFixtures(fromDate)
}