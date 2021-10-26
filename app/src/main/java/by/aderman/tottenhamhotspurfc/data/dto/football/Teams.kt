package by.aderman.tottenhamhotspurfc.data.dto.football

import com.google.gson.annotations.SerializedName

data class Teams(
    @SerializedName("away")
    val away: AwayTeam?,
    @SerializedName("home")
    val home: HomeTeam?
)