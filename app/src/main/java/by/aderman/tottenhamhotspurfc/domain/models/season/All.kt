package by.aderman.tottenhamhotspurfc.domain.models.season


import com.google.gson.annotations.SerializedName

data class All(
    val played: Int,
    val win: Int,
    val draw: Int,
    val lose: Int,
    val goals: Goals,
)