package com.suryashakti.monitor.energy

import java.time.LocalTime
import kotlin.math.round
import kotlin.random.Random

object SolarSimulator {
    fun simulateGeneration(weather: WeatherCondition): Double {
        val range = when (weather) {
            WeatherCondition.Sunny -> 5.5..8.2
            WeatherCondition.Cloudy -> 2.3..4.8
            WeatherCondition.Rainy -> 0.7..2.5
        }
        return roundOne(Random.nextDouble(range.start, range.endInclusive))
    }

    fun peakSuggestion(
        weather: WeatherCondition,
        generationKwh: Double,
        hour: Int = LocalTime.now().hour,
    ): String {
        val isPeakSun = weather == WeatherCondition.Sunny && hour in 10..15
        return if (isPeakSun || generationKwh >= 5.5) {
            "High Sun: Ideal time for heavy appliances."
        } else {
            "Save heavy loads for the next bright sun window."
        }
    }

    private fun roundOne(value: Double): Double = round(value * 10.0) / 10.0
}
