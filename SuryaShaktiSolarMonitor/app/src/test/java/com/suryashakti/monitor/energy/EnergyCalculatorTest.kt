package com.suryashakti.monitor.energy

import org.junit.Assert.assertEquals
import org.junit.Test

class EnergyCalculatorTest {
    @Test
    fun calculatesGridImportAndSavings() {
        val stats = EnergyCalculator.calculate(
            generationKwh = 4.0,
            consumptionKwh = 6.0,
            batteryPercent = 65,
            unitRate = 8.0,
        )

        assertEquals(4.0, stats.solarUsedKwh, 0.01)
        assertEquals(2.0, stats.gridImportKwh, 0.01)
        assertEquals(0.0, stats.exportToGridKwh, 0.01)
        assertEquals(32.0, stats.netSavings, 0.01)
        assertEquals(67, stats.independenceScore)
    }

    @Test
    fun handlesOverGenerationAsExportToGrid() {
        val stats = EnergyCalculator.calculate(
            generationKwh = 8.0,
            consumptionKwh = 5.0,
            batteryPercent = 90,
            unitRate = 7.0,
        )

        assertEquals(5.0, stats.solarUsedKwh, 0.01)
        assertEquals(0.0, stats.gridImportKwh, 0.01)
        assertEquals(3.0, stats.exportToGridKwh, 0.01)
        assertEquals(56.0, stats.netSavings, 0.01)
        assertEquals(100, stats.independenceScore)
    }
}
