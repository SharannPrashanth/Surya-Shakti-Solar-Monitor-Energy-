package com.suryashakti.monitor.energy

enum class WeatherCondition(val label: String) {
    Sunny("Sunny"),
    Cloudy("Cloudy"),
    Rainy("Rainy");

    companion object {
        fun fromLabel(label: String): WeatherCondition {
            return entries.firstOrNull { it.label == label } ?: Sunny
        }
    }
}
