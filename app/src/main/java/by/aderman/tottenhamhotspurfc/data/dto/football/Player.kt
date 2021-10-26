package by.aderman.tottenhamhotspurfc.data.dto.football

import com.google.gson.annotations.SerializedName

data class Player(
    @SerializedName("age")
    val age: Int?,
    @SerializedName("birth")
    val birth: Birth?,
    @SerializedName("firstname")
    val firstname: String?,
    @SerializedName("height")
    val height: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("injured")
    val injured: Boolean?,
    @SerializedName("lastname")
    val lastname: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("nationality")
    val nationality: String?,
    @SerializedName("photo")
    val photo: String?,
    @SerializedName("weight")
    val weight: String?
)