package by.aderman.tottenhamhotspurfc.domain.usecases.team

import by.aderman.tottenhamhotspurfc.domain.repositories.TeamRepository

class GetPlayerStatisticUseCase(private val repository: TeamRepository) {

    suspend fun invoke(playerId: Int) = repository.getPlayerStatistic(playerId)
}