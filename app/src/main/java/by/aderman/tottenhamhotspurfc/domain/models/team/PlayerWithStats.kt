package by.aderman.tottenhamhotspurfc.domain.models.team

data class PlayerWithStats(
    val id: Int,
    val name: String,
    val firstname: String,
    val lastname: String,
    val age: Int,
    val birth: Birth,
    val nationality: String,
    val height: String,
    val weight: String,
    val injured: Boolean,
    val photo: String,
    val statistic: Statistic
)