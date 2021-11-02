package by.aderman.tottenhamhotspurfc.data.dto.team


import com.google.gson.annotations.SerializedName

data class TeamResponse(
    @SerializedName("errors")
    val errors: List<Any>?,
    @SerializedName("get")
    val `get`: String?,
    @SerializedName("paging")
    val paging: Paging?,
    @SerializedName("parameters")
    val parameters: Parameters?,
    @SerializedName("response")
    val response: List<Response>?,
    @SerializedName("results")
    val results: Int?
)

data class Paging(
    @SerializedName("current")
    val current: Int?,
    @SerializedName("total")
    val total: Int?
)

data class Parameters(
    @SerializedName("team")
    val team: String?
)

data class Response(
    @SerializedName("players")
    val players: List<Player>?,
    @SerializedName("team")
    val team: Team?
)

data class Player(
    @SerializedName("age")
    val age: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("number")
    val number: Int?,
    @SerializedName("photo")
    val photo: String?,
    @SerializedName("position")
    val position: String?
)

data class Team(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("logo")
    val logo: String?,
    @SerializedName("name")
    val name: String?
)