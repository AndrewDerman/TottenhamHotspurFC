package by.aderman.tottenhamhotspurfc.data.dto.football.responses

import by.aderman.tottenhamhotspurfc.data.dto.football.*
import com.google.gson.annotations.SerializedName

data class PlayerResponse(
    @SerializedName("errors")
    val errors: List<Any>?,
    @SerializedName("get")
    val `get`: String?,
    @SerializedName("paging")
    val paging: Paging?,
    @SerializedName("parameters")
    val parameters: PlayerParameters?,
    @SerializedName("response")
    val response: List<ResponsePlayer>?,
    @SerializedName("results")
    val results: Int?
)

data class PlayerParameters(
    @SerializedName("id")
    val id: String?,
    @SerializedName("season")
    val season: String?,
    @SerializedName("team")
    val team: String?
)

data class ResponsePlayer(
    @SerializedName("player")
    val player: Player?,
    @SerializedName("statistics")
    val statistics: List<Statistic>?
)