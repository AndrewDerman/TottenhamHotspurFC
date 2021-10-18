package by.aderman.tottenhamhotspurfc.domain.usecases.team

import by.aderman.tottenhamhotspurfc.domain.repositories.TeamRepository

class GetTeamSquadUseCase(private val repository: TeamRepository) {
    suspend fun invoke() = repository.getTeamSquad()
}