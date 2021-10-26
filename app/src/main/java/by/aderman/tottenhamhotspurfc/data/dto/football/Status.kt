package by.aderman.tottenhamhotspurfc.data.dto.football

import com.google.gson.annotations.SerializedName

data class Status(
    @SerializedName("elapsed")
    val elapsed: Int?,
    @SerializedName("long")
    val long: String?,
    @SerializedName("short")
    val short: String?
)