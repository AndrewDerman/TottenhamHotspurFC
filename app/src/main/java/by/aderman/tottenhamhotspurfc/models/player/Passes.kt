package by.aderman.tottenhamhotspurfc.models.player


import com.google.gson.annotations.SerializedName

data class Passes(
    @SerializedName("accuracy")
    val accuracy: Int?,
    @SerializedName("key")
    val key: Int?,
    @SerializedName("total")
    val total: Int?
)