package by.aderman.tottenhamhotspurfc.data.dto.football

import com.google.gson.annotations.SerializedName

data class ColorsPlayer(
    @SerializedName("border")
    val border: String?,
    @SerializedName("number")
    val number: String?,
    @SerializedName("primary")
    val primary: String?
)