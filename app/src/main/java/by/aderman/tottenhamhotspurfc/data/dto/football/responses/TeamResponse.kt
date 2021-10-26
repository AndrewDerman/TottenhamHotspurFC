package by.aderman.tottenhamhotspurfc.data.dto.football.responses

import by.aderman.tottenhamhotspurfc.data.dto.football.Paging
import by.aderman.tottenhamhotspurfc.data.dto.football.PlayerTeam
import by.aderman.tottenhamhotspurfc.data.dto.football.Team
import com.google.gson.annotations.SerializedName

data class TeamResponse(
    @SerializedName("errors")
    val errors: List<Any>?,
    @SerializedName("get")
    val `get`: String?,
    @SerializedName("paging")
    val paging: Paging?,
    @SerializedName("parameters")
    val parameters: TeamParameters?,
    @SerializedName("response")
    val response: List<ResponseTeam>?,
    @SerializedName("results")
    val results: Int?
)

data class TeamParameters(
    @SerializedName("team")
    val team: String?
)

data class ResponseTeam(
    @SerializedName("players")
    val players: List<PlayerTeam>?,
    @SerializedName("team")
    val team: Team?
)