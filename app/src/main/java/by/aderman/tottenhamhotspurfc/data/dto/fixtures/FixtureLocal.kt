package by.aderman.tottenhamhotspurfc.data.dto.fixtures

import androidx.room.Entity
import androidx.room.PrimaryKey
import by.aderman.tottenhamhotspurfc.utils.Constants

@Entity(tableName = Constants.FIXTURE_ENTITY_TABLE_NAME)
data class FixtureLocal(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val date: String,
    var timestamp: Int,
    val timezone: String,
    val venueName: String,
    val homeTeamName: String,
    val awayTeamName: String,
    var hasAlarm: Boolean = false
)
