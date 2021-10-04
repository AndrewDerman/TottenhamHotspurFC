package by.aderman.tottenhamhotspurfc.models.team

import com.google.gson.annotations.SerializedName

data class Parameters(
    @SerializedName("team")
    val team: String?
)