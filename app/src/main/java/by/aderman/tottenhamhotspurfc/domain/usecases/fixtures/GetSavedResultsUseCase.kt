package by.aderman.tottenhamhotspurfc.domain.usecases.fixtures

import by.aderman.tottenhamhotspurfc.domain.repositories.FixturesRepository

class GetSavedResultsUseCase(private val repository: FixturesRepository) {
    suspend operator fun invoke() = repository.getSavedResults()
}