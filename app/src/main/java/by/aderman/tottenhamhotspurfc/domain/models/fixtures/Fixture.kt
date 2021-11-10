package by.aderman.tottenhamhotspurfc.domain.models.fixtures

data class Fixture(
    val id: Int,
    val status: Status,
    val date: String,
    val periods: Periods,
    val referee: String,
    val timestamp: Int,
    val timezone: String,
    val venue: Venue,
    val goals: Goals,
    val league: League,
    val score: Score,
    val teams: Teams
)