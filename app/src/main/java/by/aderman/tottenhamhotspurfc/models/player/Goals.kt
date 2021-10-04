package by.aderman.tottenhamhotspurfc.models.player


import com.google.gson.annotations.SerializedName

data class Goals(
    @SerializedName("assists")
    val assists: Int?,
    @SerializedName("conceded")
    val conceded: Int?,
    @SerializedName("saves")
    val saves: Int?,
    @SerializedName("total")
    val total: Int?
)