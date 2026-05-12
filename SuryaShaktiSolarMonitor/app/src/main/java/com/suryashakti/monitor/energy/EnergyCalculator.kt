package com.suryashakti.monitor.energy

import kotlin.math.max
import kotlin.math.min
import kotlin.math.round
import kotlin.math.roundToInt

object EnergyCalculator {
    fun calculate(
        generationKwh: Double,
        consumptionKwh: Double,
        batteryPercent: Int,
        unitRate: Double,
    ): EnergyStats {
        val generation = max(0.0, generationKwh)
        val consumption = max(0.0, consumptionKwh)
        val rate = max(0.0, unitRate)

        val solarUsed = min(generation, consumption)
        val gridImport = max(0.0, consumption - generation)
        val exportToGrid = max(0.0, generation - consumption)

        val independenceScore = when {
            consumption <= 0.0 && generation > 0.0 -> 100
            consumption <= 0.0 -> 0
            else -> ((generation / consumption) * 100.0).roundToInt().coerceIn(0, 100)
        }

        val solarShareRatio = when {
            consumption <= 0.0 && generation > 0.0 -> 1f
            consumption <= 0.0 -> 0f
            else -> (solarUsed / consumption).toFloat().coerceIn(0f, 1f)
        }

        val batteryNote = when {
            batteryPercent >= 80 -> "Battery strong"
            batteryPercent >= 35 -> "Battery moderate"
            else -> "Battery low"
        }

        val status = when {
            exportToGrid > 0.0 -> "Over-generation: exporting to grid"
            gridImport <= 0.0 && consumption > 0.0 -> "Fully solar powered today"
            gridImport > 0.0 -> "Using grid backup"
            else -> batteryNote
        }

        return EnergyStats(
            solarUsedKwh = roundOne(solarUsed),
            gridImportKwh = roundOne(gridImport),
            exportToGridKwh = roundOne(exportToGrid),
            netSavings = roundMoney((solarUsed + exportToGrid) * rate),
            gridCostAfterSolar = roundMoney(gridImport * rate),
            independenceScore = independenceScore,
            solarShareRatio = solarShareRatio,
            status = status,
        )
    }

    private fun roundOne(value: Double): Double = round(value * 10.0) / 10.0

    private fun roundMoney(value: Double): Double = round(value * 100.0) / 100.0
}
