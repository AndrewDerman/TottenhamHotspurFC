package by.aderman.tottenhamhotspurfc.data.dto.football

import com.google.gson.annotations.SerializedName

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