package by.aderman.tottenhamhotspurfc.data.dto.football

import com.google.gson.annotations.SerializedName

data class PenaltyFixture(
    @SerializedName("away")
    val away: Int?,
    @SerializedName("home")
    val home: Int?
)