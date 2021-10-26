package by.aderman.tottenhamhotspurfc.data.dto.football.responses

import by.aderman.tottenhamhotspurfc.data.dto.football.*
import com.google.gson.annotations.SerializedName

data class FixturesResponse(
    @SerializedName("errors")
    val errors: List<Any>?,
    @SerializedName("get")
    val `get`: String?,
    @SerializedName("paging")
    val paging: Paging?,
    @SerializedName("parameters")
    val parameters: FixturesParameters?,
    @SerializedName("response")
    val response: List<ResponseFixtures>?,
    @SerializedName("results")
    val results: Int?
)

data class FixturesParameters(
    @SerializedName("league")
    val league: String?,
    @SerializedName("season")
    val season: String?,
    @SerializedName("team")
    val team: String?,
    @SerializedName("from")
    val from: String?,
    @SerializedName("to")
    val to: String?
)

data class ResponseFixtures(
    @SerializedName("fixture")
    val fixture: Fixture?,
    @SerializedName("goals")
    val goals: GoalsFixtures?,
    @SerializedName("league")
    val league: LeagueWithRound?,
    @SerializedName("score")
    val score: Score?,
    @SerializedName("teams")
    val teams: Teams?
)