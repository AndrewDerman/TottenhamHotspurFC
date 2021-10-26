package by.aderman.tottenhamhotspurfc.domain.models.fixtures

data class Lineup(
    val team: Team,
    val coach: Coach,
    val formation: String?,
    val startXI: List<StartXI>?,
    val substitutes: List<Substitute>?
)