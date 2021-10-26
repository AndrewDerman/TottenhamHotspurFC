package by.aderman.tottenhamhotspurfc.data.dto.football

import com.google.gson.annotations.SerializedName

data class PlayerWithStatistic(
    @SerializedName("player")
    val player: PlayerFixtureInfo?,
    @SerializedName("statistics")
    val statistics: List<StatisticFixtureInfo>?
)