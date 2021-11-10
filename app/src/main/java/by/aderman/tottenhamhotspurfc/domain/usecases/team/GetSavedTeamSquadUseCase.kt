package by.aderman.tottenhamhotspurfc.domain.usecases.team

import by.aderman.tottenhamhotspurfc.domain.repositories.TeamRepository

class GetSavedTeamSquadUseCase(private val repository: TeamRepository) {

    suspend operator fun invoke() = repository.getSavedTeamSquad()
}