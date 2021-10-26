package by.aderman.tottenhamhotspurfc.data.dto.football

import com.google.gson.annotations.SerializedName

data class StatisticWithTypeValue(
    @SerializedName("type")
    val type: String?,
    @SerializedName("value")
    val value: Any?
)