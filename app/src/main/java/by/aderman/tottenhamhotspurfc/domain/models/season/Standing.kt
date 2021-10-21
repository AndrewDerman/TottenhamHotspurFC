package by.aderman.tottenhamhotspurfc.domain.models.season

import by.aderman.tottenhamhotspurfc.domain.models.team.Team

data class Standing(
    val rank: Int,
    val team: Team,
    val points: Int,
    val goalsDiff: Int,
    val all: All,
    val home: Home,
    val away: Away,
    val form: String,
    val update: String
)