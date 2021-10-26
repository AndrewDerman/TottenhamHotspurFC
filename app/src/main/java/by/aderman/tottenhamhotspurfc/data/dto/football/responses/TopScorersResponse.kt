package by.aderman.tottenhamhotspurfc.data.dto.football.responses

import by.aderman.tottenhamhotspurfc.data.dto.football.*
import com.google.gson.annotations.SerializedName

data class TopScorersResponse(
    @SerializedName("errors")
    val errors: List<Any>?,
    @SerializedName("get")
    val `get`: String?,
    @SerializedName("paging")
    val paging: Paging?,
    @SerializedName("parameters")
    val parameters: TopScorersParameters?,
    @SerializedName("response")
    val response: List<ResponseTopScorers>?,
    @SerializedName("results")
    val results: Int?
)

data class TopScorersParameters(
    @SerializedName("league")
    val league: String?,
    @SerializedName("season")
    val season: String?
)

data class ResponseTopScorers(
    @SerializedName("player")
    val player: Player?,
    @SerializedName("statistics")
    val statistics: List<Statistic>?
)