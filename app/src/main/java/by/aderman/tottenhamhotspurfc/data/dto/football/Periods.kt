package by.aderman.tottenhamhotspurfc.data.dto.football

import com.google.gson.annotations.SerializedName

data class Periods(
    @SerializedName("first")
    val first: Int?,
    @SerializedName("second")
    val second: Int?
)