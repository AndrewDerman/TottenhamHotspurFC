package by.aderman.tottenhamhotspurfc.models.player


import com.google.gson.annotations.SerializedName

data class Parameters(
    @SerializedName("id")
    val id: String?,
    @SerializedName("season")
    val season: String?,
    @SerializedName("team")
    val team: String?
)