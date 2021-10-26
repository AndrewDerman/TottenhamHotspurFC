package by.aderman.tottenhamhotspurfc.data.dto.football

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("assist")
    val assist: AssistEvent?,
    @SerializedName("comments")
    val comments: String?,
    @SerializedName("detail")
    val detail: String?,
    @SerializedName("player")
    val player: PlayerEvent?,
    @SerializedName("team")
    val team: Team?,
    @SerializedName("time")
    val time: Time?,
    @SerializedName("type")
    val type: String?
)