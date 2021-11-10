package by.aderman.tottenhamhotspurfc.data.db

import androidx.room.*
import by.aderman.tottenhamhotspurfc.data.dto.fixtures.FixtureLocal

@Dao
interface FixtureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFixture(fixtureLocal: FixtureLocal)

    @Query("SELECT * FROM fixtures WHERE id LIKE :fixtureId")
    suspend fun getFixtureForAlarm(fixtureId: Int): FixtureLocal

    @Query("SELECT * FROM fixtures")
    suspend fun getSavedFixtures(): List<FixtureLocal>

    @Query("DELETE FROM fixtures WHERE timestamp < :timestamp")
    suspend fun deleteOldFixtures(timestamp: Int)

    @Update
    suspend fun updateFixture(fixtureLocal: FixtureLocal)
}