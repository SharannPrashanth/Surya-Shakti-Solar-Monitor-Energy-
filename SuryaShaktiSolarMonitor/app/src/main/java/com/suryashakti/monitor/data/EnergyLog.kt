package com.suryashakti.monitor.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "energy_logs")
data class EnergyLog(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: Long,
    val generation: Double,
    val consumption: Double,
    val batteryStart: Int, // %
    val batteryEnd: Int,   // %
    val solarUsed: Double,
    val gridUsed: Double,
    val exported: Double,
    val savings: Double,
    val weather: String
)
