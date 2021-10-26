package by.aderman.tottenhamhotspurfc.domain.usecases.fixtures

import by.aderman.tottenhamhotspurfc.domain.repositories.FixturesRepository

class GetResultsUseCase(private val repository: FixturesRepository) {
    suspend operator fun invoke(toDate: String) = repository.getResults(toDate)
}