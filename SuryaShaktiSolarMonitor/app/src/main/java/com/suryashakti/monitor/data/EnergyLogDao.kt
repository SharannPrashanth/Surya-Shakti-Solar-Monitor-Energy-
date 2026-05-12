package com.suryashakti.monitor.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EnergyLogDao {
    @Query("SELECT * FROM energy_logs ORDER BY date DESC LIMIT 30")
    fun observeLast30(): Flow<List<EnergyLog>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(log: EnergyLog)

    @Query("DELETE FROM energy_logs")
    suspend fun clearAll()
}
