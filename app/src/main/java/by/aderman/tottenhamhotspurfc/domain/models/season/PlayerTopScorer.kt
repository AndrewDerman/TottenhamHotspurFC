package by.aderman.tottenhamhotspurfc.domain.models.season

import by.aderman.tottenhamhotspurfc.domain.models.team.Team

data class PlayerTopScorer(
    val rank: Int,
    val name: String,
    val photo: String,
    val team: Team,
    val goals: Int,
    val penalties: Int
)