package com.suryashakti.monitor.data

import kotlinx.coroutines.flow.Flow

class EnergyRepository(private val energyDao: EnergyDao) {
    val allLogs: Flow<List<EnergyLog>> = energyDao.getAllLogs()
    val last30DaysLogs: Flow<List<EnergyLog>> = energyDao.getLast30DaysLogs()

    suspend fun insert(log: EnergyLog) {
        energyDao.insertLog(log)
    }

    suspend fun update(log: EnergyLog) {
        energyDao.updateLog(log)
    }

    suspend fun delete(log: EnergyLog) {
        energyDao.deleteLog(log)
    }

    suspend fun deleteAll() {
        energyDao.deleteAllLogs()
    }
}
