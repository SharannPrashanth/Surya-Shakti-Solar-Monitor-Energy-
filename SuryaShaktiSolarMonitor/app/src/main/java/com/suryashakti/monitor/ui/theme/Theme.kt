package com.suryashakti.monitor.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Black = Color(0xFF000000)
private val Yellow = Color(0xFFFFD700) // High contrast Yellow

private val ColorScheme = darkColorScheme(
    primary = Yellow,
    onPrimary = Black,
    background = Black,
    onBackground = Yellow,
    surface = Black,
    onSurface = Yellow
)

@Composable
fun SuryaShaktiTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ColorScheme,
        content = content
    )
}
