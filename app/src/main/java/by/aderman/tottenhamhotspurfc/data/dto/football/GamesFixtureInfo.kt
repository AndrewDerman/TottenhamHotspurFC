package by.aderman.tottenhamhotspurfc.data.dto.football

import com.google.gson.annotations.SerializedName

data class GamesFixtureInfo(
    @SerializedName("captain")
    val captain: Boolean?,
    @SerializedName("minutes")
    val minutes: Int?,
    @SerializedName("number")
    val number: Int?,
    @SerializedName("position")
    val position: String?,
    @SerializedName("rating")
    val rating: String?,
    @SerializedName("substitute")
    val substitute: Boolean?
)