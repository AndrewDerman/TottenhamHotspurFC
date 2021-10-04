package by.aderman.tottenhamhotspurfc.models.team

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("logo")
    val logo: String?,
    @SerializedName("name")
    val name: String?
)