package by.aderman.tottenhamhotspurfc.data.dto.football

import com.google.gson.annotations.SerializedName

data class PlayerEvent(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
)