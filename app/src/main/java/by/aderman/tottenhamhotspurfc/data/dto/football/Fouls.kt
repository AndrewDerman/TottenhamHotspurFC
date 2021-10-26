package by.aderman.tottenhamhotspurfc.data.dto.football

import com.google.gson.annotations.SerializedName

data class Fouls(
    @SerializedName("committed")
    val committed: Int?,
    @SerializedName("drawn")
    val drawn: Int?
)