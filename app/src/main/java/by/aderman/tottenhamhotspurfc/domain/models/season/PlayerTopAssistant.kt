package by.aderman.tottenhamhotspurfc.domain.models.season

import by.aderman.tottenhamhotspurfc.domain.models.team.Team

data class PlayerTopAssistant(
    val rank: Int,
    val name: String,
    val photo: String,
    val team: Team,
    val assists: Int
)