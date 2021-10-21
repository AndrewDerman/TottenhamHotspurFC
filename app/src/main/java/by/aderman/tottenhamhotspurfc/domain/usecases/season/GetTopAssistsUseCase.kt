package by.aderman.tottenhamhotspurfc.domain.usecases.season

import by.aderman.tottenhamhotspurfc.domain.repositories.SeasonRepository

class GetTopAssistsUseCase(private val repository: SeasonRepository) {
    suspend operator fun invoke() = repository.getTopAssists()
}