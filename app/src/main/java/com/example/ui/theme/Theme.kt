package com.example.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = OlivePrimary,
    onPrimary = Color.White,
    primaryContainer = OliveContainer,
    onPrimaryContainer = OliveDeep,
    secondary = GoldWax,
    onSecondary = Color.White,
    secondaryContainer = WarmBeige,
    onSecondaryContainer = EarthBrown,
    tertiary = RoseWax,
    onTertiary = Color.White,
    background = LinenBackground,
    onBackground = CharcoalDark,
    surface = CreamPaper,
    onSurface = CharcoalDark,
    surfaceVariant = WarmBeige,
    onSurfaceVariant = WarmMuted,
    outline = BorderWarm,
    outlineVariant = SoftSand
)

private val DarkColorScheme = darkColorScheme(
    primary = OliveLight,
    onPrimary = Color.White,
    primaryContainer = OliveDeep,
    onPrimaryContainer = OliveContainer,
    secondary = GoldWaxLight,
    onSecondary = CharcoalDark,
    secondaryContainer = EarthBrown,
    onSecondaryContainer = WarmBeige,
    tertiary = RoseWax,
    onTertiary = Color.White,
    background = Color(0xFF1B1F1A),
    onBackground = Color(0xFFF3EDE2),
    surface = Color(0xFF232722),
    onSurface = Color(0xFFF3EDE2),
    surfaceVariant = Color(0xFF2D332C),
    onSurfaceVariant = Color(0xFFCCC5B7),
    outline = Color(0xFF495247)
)

@Composable
fun TatianaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            window?.let {
                it.statusBarColor = colorScheme.background.toArgb()
                it.navigationBarColor = colorScheme.background.toArgb()
                WindowCompat.getInsetsController(it, view).isAppearanceLightStatusBars = !darkTheme
                WindowCompat.getInsetsController(it, view).isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = TatianaTypography,
        content = content
    )
}

// Alias for backwards compatibility with template tests
@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    TatianaTheme(darkTheme = darkTheme, content = content)
}
