package com.nj.space.ui.theme


import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext


private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFE8871E),           // warm amber-orange, carries the joy without contrast problems
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFDDB3),
    onPrimaryContainer = Color(0xFF442B00),

    secondary = Color(0xFF735943),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFFDCC1),
    onSecondaryContainer = Color(0xFF291805),

    tertiary = Color(0xFFFFC839),            // your yellow, used sparingly — badges, highlights, celebratory states
    onTertiary = Color(0xFF3F2E00),
    tertiaryContainer = Color(0xFFFFE08B),
    onTertiaryContainer = Color(0xFF261A00),

    background = Color(0xFFFFFBF5),
    onBackground = Color(0xFF201B13),
    surface = Color(0xFFFFFBF5),
    onSurface = Color(0xFF201B13),
    surfaceVariant = Color(0xFFF0E0CE),
    onSurfaceVariant = Color(0xFF50453A),

    error = Color(0xFFBA1A1A),
    onError = Color.White
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFFFB870),
    onPrimary = Color(0xFF482900),
    primaryContainer = Color(0xFF663D00),
    onPrimaryContainer = Color(0xFFFFDDB3),

    secondary = Color(0xFFE3C1A5),
    onSecondary = Color(0xFF412C1A),
    secondaryContainer = Color(0xFF59422E),
    onSecondaryContainer = Color(0xFFFFDCC1),

    tertiary = Color(0xFFECC148),
    onTertiary = Color(0xFF3F2E00),
    tertiaryContainer = Color(0xFF5B4300),
    onTertiaryContainer = Color(0xFFFFE08B),

    background = Color(0xFF17130D),
    onBackground = Color(0xFFEBE1D6),
    surface = Color(0xFF17130D),
    onSurface = Color(0xFFEBE1D6),
    surfaceVariant = Color(0xFF50453A),
    onSurfaceVariant = Color(0xFFD3C4B4),

    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005)
)

@Composable
fun SocialSpaceTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // turn off until you actually want per-device theming
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}