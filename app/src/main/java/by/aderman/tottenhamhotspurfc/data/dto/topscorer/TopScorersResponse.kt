package by.aderman.tottenhamhotspurfc.data.dto.topscorer

import com.google.gson.annotations.SerializedName

data class TopScorersResponse(
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
    @SerializedName("league")
    val league: String?,
    @SerializedName("season")
    val season: String?
)

data class Response(
    @SerializedName("player")
    val player: Player?,
    @SerializedName("statistics")
    val statistics: List<Statistic>?
)

data class Player(
    @SerializedName("age")
    val age: Int?,
    @SerializedName("birth")
    val birth: Birth?,
    @SerializedName("firstname")
    val firstname: String?,
    @SerializedName("height")
    val height: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("injured")
    val injured: Boolean?,
    @SerializedName("lastname")
    val lastname: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("nationality")
    val nationality: String?,
    @SerializedName("photo")
    val photo: String?,
    @SerializedName("weight")
    val weight: String?
)

data class Statistic(
    @SerializedName("cards")
    val cards: Cards?,
    @SerializedName("dribbles")
    val dribbles: Dribbles?,
    @SerializedName("duels")
    val duels: Duels?,
    @SerializedName("fouls")
    val fouls: Fouls?,
    @SerializedName("games")
    val games: Games?,
    @SerializedName("goals")
    val goals: Goals?,
    @SerializedName("league")
    val league: League?,
    @SerializedName("passes")
    val passes: Passes?,
    @SerializedName("penalty")
    val penalty: Penalty?,
    @SerializedName("shots")
    val shots: Shots?,
    @SerializedName("substitutes")
    val substitutes: Substitutes?,
    @SerializedName("tackles")
    val tackles: Tackles?,
    @SerializedName("team")
    val team: Team?
)

data class Birth(
    @SerializedName("country")
    val country: String?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("place")
    val place: String?
)

data class Cards(
    @SerializedName("red")
    val red: Int?,
    @SerializedName("yellow")
    val yellow: Int?,
    @SerializedName("yellowred")
    val yellowred: Int?
)

data class Dribbles(
    @SerializedName("attempts")
    val attempts: Int?,
    @SerializedName("past")
    val past: Any?,
    @SerializedName("success")
    val success: Int?
)

data class Duels(
    @SerializedName("total")
    val total: Int?,
    @SerializedName("won")
    val won: Int?
)

data class Fouls(
    @SerializedName("committed")
    val committed: Int?,
    @SerializedName("drawn")
    val drawn: Int?
)

data class Games(
    @SerializedName("appearences")
    val appearences: Int?,
    @SerializedName("captain")
    val captain: Boolean?,
    @SerializedName("lineups")
    val lineups: Int?,
    @SerializedName("minutes")
    val minutes: Int?,
    @SerializedName("number")
    val number: Any?,
    @SerializedName("position")
    val position: String?,
    @SerializedName("rating")
    val rating: String?
)

data class Goals(
    @SerializedName("assists")
    val assists: Int?,
    @SerializedName("conceded")
    val conceded: Int?,
    @SerializedName("saves")
    val saves: Any?,
    @SerializedName("total")
    val total: Int?
)

data class League(
    @SerializedName("country")
    val country: String?,
    @SerializedName("flag")
    val flag: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("logo")
    val logo: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("season")
    val season: Int?
)

data class Passes(
    @SerializedName("accuracy")
    val accuracy: Int?,
    @SerializedName("key")
    val key: Int?,
    @SerializedName("total")
    val total: Int?
)

data class Penalty(
    @SerializedName("commited")
    val commited: Any?,
    @SerializedName("missed")
    val missed: Int?,
    @SerializedName("saved")
    val saved: Any?,
    @SerializedName("scored")
    val scored: Int?,
    @SerializedName("won")
    val won: Any?
)

data class Shots(
    @SerializedName("on")
    val on: Int?,
    @SerializedName("total")
    val total: Int?
)

data class Substitutes(
    @SerializedName("bench")
    val bench: Int?,
    @SerializedName("in")
    val inX: Int?,
    @SerializedName("out")
    val `out`: Int?
)

data class Tackles(
    @SerializedName("blocks")
    val blocks: Any?,
    @SerializedName("interceptions")
    val interceptions: Any?,
    @SerializedName("total")
    val total: Any?
)

data class Team(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("logo")
    val logo: String?,
    @SerializedName("name")
    val name: String?
)

