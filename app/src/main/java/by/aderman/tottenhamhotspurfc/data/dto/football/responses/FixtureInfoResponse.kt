package by.aderman.tottenhamhotspurfc.data.dto.football.responses

import by.aderman.tottenhamhotspurfc.data.dto.football.*
import com.google.gson.annotations.SerializedName

data class FixtureInfoResponse(
    @SerializedName("errors")
    val errors: List<Any>?,
    @SerializedName("get")
    val `get`: String?,
    @SerializedName("paging")
    val paging: Paging?,
    @SerializedName("parameters")
    val parameters: FixtureInfoParameters?,
    @SerializedName("response")
    val response: List<ResponseFixturesInfo>?,
    @SerializedName("results")
    val results: Int?
)

data class FixtureInfoParameters(
    @SerializedName("id")
    val id: String?
)

data class ResponseFixturesInfo(
    @SerializedName("events")
    val events: List<Event>?,
    @SerializedName("fixture")
    val fixture: Fixture?,
    @SerializedName("goals")
    val goals: GoalsFixtures?,
    @SerializedName("league")
    val league: LeagueWithRound?,
    @SerializedName("lineups")
    val lineups: List<Lineup>?,
    @SerializedName("players")
    val players: List<StatisticPlayer>?,
    @SerializedName("score")
    val score: Score?,
    @SerializedName("statistics")
    val statistics: List<StatisticTeam>?,
    @SerializedName("teams")
    val teams: Teams?
)