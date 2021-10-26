package by.aderman.tottenhamhotspurfc.data.dto.football

import com.google.gson.annotations.SerializedName

data class Games(
    @SerializedName("appearences")
    val appearences: Int?,
    @SerializedName("captain")
    val captain: Boolean?,
    @SerializedName("lineups")
    val lineups: Int?,
    @SerializedName("minutes")
    val minutes: Int?,
    @SerializedName("number")
    val number: Int?,
    @SerializedName("position")
    val position: String?,
    @SerializedName("rating")
    val rating: String?
)