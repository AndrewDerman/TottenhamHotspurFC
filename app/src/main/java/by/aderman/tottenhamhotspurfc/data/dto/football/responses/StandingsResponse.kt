package by.aderman.tottenhamhotspurfc.data.dto.football.responses

import by.aderman.tottenhamhotspurfc.data.dto.football.*
import com.google.gson.annotations.SerializedName

data class StandingsResponse(
    @SerializedName("errors")
    val errors: List<Any>?,
    @SerializedName("get")
    val `get`: String?,
    @SerializedName("paging")
    val paging: Paging?,
    @SerializedName("parameters")
    val parameters: StandingsParameters?,
    @SerializedName("response")
    val response: List<ResponseStandings>?,
    @SerializedName("results")
    val results: Int?
)

data class StandingsParameters(
    @SerializedName("league")
    val league: String?,
    @SerializedName("season")
    val season: String?
)

data class ResponseStandings(
    @SerializedName("league")
    val league: LeagueWithStandings?
)