package by.aderman.tottenhamhotspurfc.data.dto.football

import com.google.gson.annotations.SerializedName

data class Shots(
    @SerializedName("on")
    val on: Int?,
    @SerializedName("total")
    val total: Int?
)