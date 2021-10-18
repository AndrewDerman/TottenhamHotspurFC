package by.aderman.tottenhamhotspurfc.domain.models.team

data class Penalty(
    val commited: Int,
    val missed: Int,
    val saved: Int,
    val scored: Int,
    val won: Int
)