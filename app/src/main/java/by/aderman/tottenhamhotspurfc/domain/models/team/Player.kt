package by.aderman.tottenhamhotspurfc.domain.models.team

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    val id: Int,
    val name: String,
    val age: Int,
    val number: Int?,
    val position: String,
    val photo: String
) : Parcelable