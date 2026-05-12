package com.suryashakti.monitor.data

import android.content.Context
import android.content.SharedPreferences

class AppPreferences(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("surya_shakti_prefs", Context.MODE_PRIVATE)

    var electricityRate: Double
        get() = prefs.getFloat("electricity_rate", 6.0f).toDouble()
        set(value) = prefs.edit().putFloat("electricity_rate", value.toFloat()).apply()

    fun resetAll() {
        prefs.edit().clear().apply()
    }
}
