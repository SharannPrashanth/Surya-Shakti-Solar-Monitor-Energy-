package com.suryashakti.monitor.energy

data class EnergyStats(
    val solarUsedKwh: Double,
    val gridImportKwh: Double,
    val exportToGridKwh: Double,
    val netSavings: Double,
    val gridCostAfterSolar: Double,
    val independenceScore: Int,
    val solarShareRatio: Float,
    val status: String,
)
