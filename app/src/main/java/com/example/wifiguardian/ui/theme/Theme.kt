package com.example.wifiguardian.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF7DD3FC),
    secondary = Color(0xFF93C5FD),
    tertiary = Color(0xFFBFDBFE)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF2563EB),
    secondary = Color(0xFF3B82F6),
    tertiary = Color(0xFF60A5FA)
)

@Composable
fun WiFiGuardianTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
