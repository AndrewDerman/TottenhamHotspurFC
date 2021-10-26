package by.aderman.tottenhamhotspurfc.data.dto.football

import com.google.gson.annotations.SerializedName

data class StatisticFixtureInfo(
    @SerializedName("cards")
    val cards: CardsFixtureInfo?,
    @SerializedName("dribbles")
    val dribbles: Dribbles?,
    @SerializedName("duels")
    val duels: Duels?,
    @SerializedName("fouls")
    val fouls: Fouls?,
    @SerializedName("games")
    val games: GamesFixtureInfo?,
    @SerializedName("goals")
    val goals: Goals?,
    @SerializedName("offsides")
    val offsides: Int?,
    @SerializedName("passes")
    val passes: Passes?,
    @SerializedName("penalty")
    val penalty: Penalty?,
    @SerializedName("shots")
    val shots: Shots?,
    @SerializedName("tackles")
    val tackles: Tackles?
)