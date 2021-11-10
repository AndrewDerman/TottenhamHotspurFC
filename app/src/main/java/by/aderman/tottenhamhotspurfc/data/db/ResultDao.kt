package by.aderman.tottenhamhotspurfc.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.aderman.tottenhamhotspurfc.data.dto.fixtures.ResultLocal

@Dao
interface ResultDao {

    @Query("SELECT * FROM results")
    suspend fun getSavedResults(): List<ResultLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveResult(result: ResultLocal)
}
