package by.aderman.tottenhamhotspurfc.data.dto.team

import androidx.room.Entity
import androidx.room.PrimaryKey
import by.aderman.tottenhamhotspurfc.utils.Constants

@Entity(tableName = Constants.PLAYER_ENTITY_TABLE_NAME)
data class PlayerLocal(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val age: Int,
    val number: Int,
    val position: String,
    val photo: String
)