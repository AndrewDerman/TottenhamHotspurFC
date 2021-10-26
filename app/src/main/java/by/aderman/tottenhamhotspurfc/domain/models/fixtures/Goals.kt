package by.aderman.tottenhamhotspurfc.domain.models.fixtures

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Goals(val away: Int, val home: Int) : Parcelable