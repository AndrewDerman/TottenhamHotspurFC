package by.aderman.tottenhamhotspurfc.data.dto.fixtures

import androidx.room.Entity
import androidx.room.PrimaryKey
import by.aderman.tottenhamhotspurfc.utils.Constants

@Entity(tableName = Constants.RESULT_ENTITY_TABLE_NAME)
data class ResultLocal(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val elapsedTime: Int,
    val statusLongValue: String,
    val statusShortValue: String,
    val date: String,
    val firstPeriod: Int,
    val secondPeriod: Int,
    val referee: String,
    val timestamp: Int,
    val timezone: String,
    val venueId: Int,
    val venueCity: String,
    val venueName: String,
    val goalsAway: Int,
    val goalsHome: Int,
    val leagueId: Int,
    val leagueCountry: String,
    val leagueFlag: String,
    val leagueLogo: String,
    val leagueName: String,
    val leagueSeason: Int,
    val leagueRound: String,
    val scoreExtratimeAway: Int,
    val scoreExtratimeHome: Int,
    val scoreFulltimeAway: Int,
    val scoreFulltimeHome: Int,
    val scoreHalftimeAway: Int,
    val scoreHalftimeHome: Int,
    val scorePenaltyAway: Int,
    val scorePenaltyHome: Int,
    val teamAwayId: Int,
    val teamAwayLogo: String,
    val teamAwayName: String,
    val teamAwayWinner: Boolean,
    val teamHomeId: Int,
    val teamHomeLogo: String,
    val teamHomeName: String,
    val teamHomeWinner: Boolean
)