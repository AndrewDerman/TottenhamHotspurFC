package by.aderman.tottenhamhotspurfc.data.dto.football

import com.google.gson.annotations.SerializedName

data class Substitute(
    @SerializedName("player")
    val player: PlayerSubstitute?
)