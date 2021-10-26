package by.aderman.tottenhamhotspurfc.data.dto.football

import com.google.gson.annotations.SerializedName

data class Time(
    @SerializedName("elapsed")
    val elapsed: Int?,
    @SerializedName("extra")
    val extra: Int?
)