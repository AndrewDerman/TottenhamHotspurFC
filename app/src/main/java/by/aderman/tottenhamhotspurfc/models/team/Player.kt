package by.aderman.tottenhamhotspurfc.models.team

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
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
) : Parcelable