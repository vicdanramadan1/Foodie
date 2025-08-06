package org.betaapps.foodie.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Primary branding colors
val OrangePrimary = Color(0xFFDD571C) // Deep orange
val GreenSecondary = Color(0xFF66BB6A) // Fresh green
val CreamBackground = Color(0xFFFFF8F2) // Light cream
val ErrorColor = Color(0xFFD32F2F) // Tomato red

private val LightColorScheme = lightColorScheme(
    primary = OrangePrimary,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFCC80),
    onPrimaryContainer = Color.Black,
    secondary = GreenSecondary,
    onSecondary = Color.White,
    background = CreamBackground,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    error = ErrorColor,
    onError = Color.White
)

private val DarkColorScheme = darkColorScheme(
    primary = OrangePrimary,
    onPrimary = Color.Black,
    primaryContainer = Color(0xFFBF360C),
    onPrimaryContainer = Color.White,
    secondary = GreenSecondary,
    onSecondary = Color.Black,
    background = Color(0xFF121212),
    onBackground = Color.White,
    surface = Color(0xFF1E1E1E),
    onSurface = Color.White,
    error = ErrorColor,
    onError = Color.Black
)

@Composable
fun FoodAppTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography(),
        content = content
    )
}
