package by.aderman.tottenhamhotspurfc.domain.models.season

data class Home(
    val played: Int,
    val win: Int,
    val draw: Int,
    val lose: Int,
    val goals: Goals
)