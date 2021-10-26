package by.aderman.tottenhamhotspurfc.data.dto.football

import com.google.gson.annotations.SerializedName

data class StatisticPlayer(
    @SerializedName("players")
    val players: List<PlayerWithStatistic>?,
    @SerializedName("team")
    val team: TeamWithUpdateTime?
)