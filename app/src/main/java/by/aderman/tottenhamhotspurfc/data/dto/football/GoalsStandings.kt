package by.aderman.tottenhamhotspurfc.data.dto.football

import com.google.gson.annotations.SerializedName

data class GoalsStandings(
    @SerializedName("against")
    val against: Int?,
    @SerializedName("for")
    val forX: Int?
)