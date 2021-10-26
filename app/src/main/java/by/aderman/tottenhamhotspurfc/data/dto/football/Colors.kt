package by.aderman.tottenhamhotspurfc.data.dto.football

import com.google.gson.annotations.SerializedName

data class Colors(
    @SerializedName("goalkeeper")
    val goalkeeper: ColorsGoalkeeper?,
    @SerializedName("player")
    val player: ColorsPlayer?
)