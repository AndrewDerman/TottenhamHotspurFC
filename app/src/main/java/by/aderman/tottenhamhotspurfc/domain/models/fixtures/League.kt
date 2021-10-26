package by.aderman.tottenhamhotspurfc.domain.models.fixtures

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class League(
    val id: Int,
    val country: String,
    val flag: String,
    val logo: String,
    val name: String,
    val season: Int,
    val round: String
) : Parcelable