package com.suryashakti.monitor.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.suryashakti.monitor.data.AppPreferences
import com.suryashakti.monitor.data.EnergyLog
import com.suryashakti.monitor.data.EnergyRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.math.min

class EnergyViewModel(
    private val repository: EnergyRepository,
    private val preferences: AppPreferences
) : ViewModel() {

    val allLogs: StateFlow<List<EnergyLog>> = repository.allLogs
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val last30DaysLogs: StateFlow<List<EnergyLog>> = repository.last30DaysLogs
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val totalSavings: StateFlow<Double> = last30DaysLogs.map { logs ->
        logs.sumOf { it.savings }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)

    val electricityRate = MutableStateFlow(preferences.electricityRate)

    fun updateRate(rate: Double) {
        preferences.electricityRate = rate
        electricityRate.value = rate
    }

    fun calculateAndSaveLog(
        id: Int = 0,
        date: Long = System.currentTimeMillis(),
        generation: Double,
        consumption: Double,
        batteryStart: Int,
        weather: String
    ) {
        viewModelScope.launch {
            val rate = preferences.electricityRate
            val batteryCapacityKwh = 10.0 // Assumed capacity for logic
            val currentBatteryKwh = (batteryStart / 100.0) * batteryCapacityKwh

            // Logic:
            // 1. Solar energy is used first for consumption.
            val solarUsed = min(generation, consumption)
            var extraSolar = if (generation > consumption) generation - consumption else 0.0

            // 4. (Partial) If solar insufficient, remaining is grid.
            val gridUsed = if (consumption > generation) consumption - generation else 0.0

            // 2. Extra charges battery until full.
            val neededToFull = batteryCapacityKwh - currentBatteryKwh
            val charging = min(extraSolar, neededToFull)
            extraSolar -= charging
            val batteryEndKwh = currentBatteryKwh + charging
            val batteryEndPercent = ((batteryEndKwh / batteryCapacityKwh) * 100).toInt().coerceIn(0, 100)

            // 3. If battery is full: Extra energy is exported to grid.
            val exported = extraSolar

            // Formulas:
            // Savings = (SolarUsed × Rate) + (Exported × Rate × 0.5)
            val savings = (solarUsed * rate) + (exported * rate * 0.5)

            val log = EnergyLog(
                id = id,
                date = date,
                generation = generation,
                consumption = consumption,
                batteryStart = batteryStart,
                batteryEnd = batteryEndPercent,
                solarUsed = solarUsed,
                gridUsed = gridUsed,
                exported = exported,
                savings = savings,
                weather = weather
            )
            
            if (id == 0) {
                repository.insert(log)
            } else {
                repository.update(log)
            }
        }
    }

    fun deleteLog(log: EnergyLog) {
        viewModelScope.launch {
            repository.delete(log)
        }
    }

    fun resetData() {
        viewModelScope.launch {
            repository.deleteAll()
            preferences.resetAll()
            electricityRate.value = 6.0
        }
    }

    fun simulateDay(): Pair<String, Double> {
        val conditions = listOf(
            Triple("Sunny", 6.0, 10.0),
            Triple("Cloudy", 3.0, 6.0),
            Triple("Rainy", 1.0, 3.0)
        )
        val condition = conditions.random()
        val gen = condition.second + (Math.random() * (condition.third - condition.second))
        return condition.first to String.format("%.2f", gen).toDouble()
    }

    fun getLogById(id: Int): Flow<EnergyLog?> {
        return allLogs.map { logs -> logs.find { it.id == id } }
    }
}

class EnergyViewModelFactory(
    private val repository: EnergyRepository,
    private val preferences: AppPreferences
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EnergyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EnergyViewModel(repository, preferences) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
