package by.aderman.tottenhamhotspurfc.domain.models.fixtures

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Status(
    val elapsed: Int?,
    val longValue: String,
    val shortValue: String
) : Parcelable