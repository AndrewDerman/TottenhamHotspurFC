package by.aderman.tottenhamhotspurfc.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.aderman.tottenhamhotspurfc.data.dto.team.PlayerLocal

@Dao
interface PlayerDao {

    @Query("SELECT * FROM players ORDER BY number")
    suspend fun getSavedTeamSquad(): List<PlayerLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePlayer(player: PlayerLocal)
}