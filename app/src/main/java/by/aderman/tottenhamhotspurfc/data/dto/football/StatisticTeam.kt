package by.aderman.tottenhamhotspurfc.data.dto.football

import com.google.gson.annotations.SerializedName

data class StatisticTeam(
    @SerializedName("statistics")
    val statistics: List<StatisticWithTypeValue>?,
    @SerializedName("team")
    val team: Team?
)