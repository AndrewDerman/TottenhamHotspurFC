package by.aderman.tottenhamhotspurfc.data.dto.football

import com.google.gson.annotations.SerializedName

data class Dribbles(
    @SerializedName("attempts")
    val attempts: Int?,
    @SerializedName("past")
    val past: Int?,
    @SerializedName("success")
    val success: Int?
)