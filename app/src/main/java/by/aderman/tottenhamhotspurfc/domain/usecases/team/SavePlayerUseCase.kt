package by.aderman.tottenhamhotspurfc.domain.usecases.team

import by.aderman.tottenhamhotspurfc.domain.models.team.Player
import by.aderman.tottenhamhotspurfc.domain.repositories.TeamRepository

class SavePlayerUseCase(private val repository: TeamRepository) {
    suspend operator fun invoke(player: Player) = repository.savePlayer(player)
}