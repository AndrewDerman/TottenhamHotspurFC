package by.aderman.tottenhamhotspurfc.domain.models.team

data class Games(
    val appearences: Int,
    val captain: Boolean,
    val lineups: Int,
    val minutes: Int,
    val number: Int,
    val position: String,
    val rating: String
)