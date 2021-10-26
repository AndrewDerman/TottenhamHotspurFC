package by.aderman.tottenhamhotspurfc.domain.models.fixtures

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Away(
    val id: Int,
    val logo: String,
    val name: String,
    val winner: Boolean
) : Parcelable