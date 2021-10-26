package by.aderman.tottenhamhotspurfc.domain.models.fixtures

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Teams(val away: Away, val home: Home) : Parcelable