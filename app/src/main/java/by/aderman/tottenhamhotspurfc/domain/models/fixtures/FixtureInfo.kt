package by.aderman.tottenhamhotspurfc.domain.models.fixtures

data class FixtureInfo(
    val id: Int,
    val status: Status,
    val date: String,
    val periods: Periods,
    val referee: String?,
    val timestamp: Int,
    val timezone: String,
    val venue: Venue,
    val goals: Goals,
    val league: League,
    val score: Score,
    val teams: Teams,
    val events: List<Event>?,
    val lineups: List<Lineup>?,
    val statistics: List<Statistics>?
)