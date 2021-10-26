package by.aderman.tottenhamhotspurfc.data.dto.football

import com.google.gson.annotations.SerializedName

data class Lineup(
    @SerializedName("coach")
    val coach: Coach?,
    @SerializedName("formation")
    val formation: String?,
    @SerializedName("startXI")
    val startXI: List<StartXI>?,
    @SerializedName("substitutes")
    val substitutes: List<Substitute>?,
    @SerializedName("team")
    val team: TeamWithColors?
)