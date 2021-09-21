package by.aderman.tottenhamhotspurfc.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Source(
    @SerializedName("id")
    // автоматически тип был Any
    val id: String?,
    @SerializedName("name")
    val name: String?
) : Parcelable