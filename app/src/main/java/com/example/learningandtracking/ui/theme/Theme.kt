package com.example.learningandtracking.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF80CBC4),
    secondary = Color(0xFFB39DDB),
    tertiary = Color(0xFFFFAB91)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF006D77),
    secondary = Color(0xFF7F5AF0),
    tertiary = Color(0xFFEE6C4D),
    background = Color(0xFFF6FAFD)
)

@Composable
fun LearningAndTrackingTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

