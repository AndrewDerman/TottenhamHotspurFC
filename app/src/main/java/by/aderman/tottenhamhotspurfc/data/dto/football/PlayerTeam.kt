package by.aderman.tottenhamhotspurfc.data.dto.football

import com.google.gson.annotations.SerializedName

data class PlayerTeam(
    @SerializedName("age")
    val age: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("number")
    val number: Int?,
    @SerializedName("photo")
    val photo: String?,
    @SerializedName("position")
    val position: String?
)