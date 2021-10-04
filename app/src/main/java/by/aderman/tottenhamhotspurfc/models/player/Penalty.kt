package by.aderman.tottenhamhotspurfc.models.player


import com.google.gson.annotations.SerializedName

data class Penalty(
    @SerializedName("commited")
    val commited: Int?,
    @SerializedName("missed")
    val missed: Int?,
    @SerializedName("saved")
    val saved: Int?,
    @SerializedName("scored")
    val scored: Int?,
    @SerializedName("won")
    val won: Int?
)