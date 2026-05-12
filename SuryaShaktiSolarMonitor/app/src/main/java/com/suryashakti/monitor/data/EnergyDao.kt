package com.suryashakti.monitor.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface EnergyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(log: EnergyLog)

    @Update
    suspend fun updateLog(log: EnergyLog)

    @Delete
    suspend fun deleteLog(log: EnergyLog)

    @Query("SELECT * FROM energy_logs ORDER BY date DESC")
    fun getAllLogs(): Flow<List<EnergyLog>>

    @Query("SELECT * FROM energy_logs ORDER BY date DESC LIMIT 30")
    fun getLast30DaysLogs(): Flow<List<EnergyLog>>

    @Query("DELETE FROM energy_logs")
    suspend fun deleteAllLogs()
}
