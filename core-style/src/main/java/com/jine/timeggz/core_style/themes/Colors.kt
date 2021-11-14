package com.jine.timeggz.core_style.themes

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Theme colors

val Primary20 = Color(0xFF432C00)
val Primary30 = Color(0xFF604100)
val Primary80 = Color(0xFFFFBA25)
val Primary90 = Color(0xFFFFDEA7)

val Secondary20 = Color(0xFF3C2E16)
val Secondary30 = Color(0xFF54442A)
val Secondary80 = Color(0xFFDAC3A0)
val Secondary90 = Color(0xFFF8DFBB)

val Tertiary20 = Color(0xFF213619)
val Tertiary30 = Color(0xFF374D2D)
val Tertiary80 = Color(0xFFB4CEA5)
val Tertiary90 = Color(0xFFD0EAC0)

val Error20 = Color(0xFF680003)
val Error30 = Color(0xFF930006)
val Error80 = Color(0xFFFFB4A9)
val Error90 = Color(0xFFFFDAD4)

val Neutral10 = Color(0xFF1F1B16)
val Neutral90 = Color(0xFFEAE1D8)

val NeutralVariant30 = Color(0xFF4E4538)
val NeutralVariant50 = Color(0xFF807567)
val NeutralVariant90 = Color(0xFFEEE0CF)

val LightColorPalette = lightColorScheme(
    // Primary
    primary = Primary80,
    onPrimary = Primary20,
    primaryContainer = Primary30,
    onPrimaryContainer = Primary90,
    // Secondary
    secondary = Secondary80,
    onSecondary = Secondary20,
    secondaryContainer = Secondary30,
    onSecondaryContainer = Secondary90,
    // Tertiary
    tertiary = Tertiary80,
    onTertiary = Tertiary20,
    tertiaryContainer = Tertiary30,
    onTertiaryContainer = Tertiary90,
    // Error
    error = Error80,
    onError = Error20,
    errorContainer = Error30,
    onErrorContainer = Error90,
    // Background and surface
    background = Neutral90,
    onBackground = Neutral10,
    surface = Neutral90,
    onSurface = Neutral10,
    surfaceVariant = NeutralVariant90,
    onSurfaceVariant = NeutralVariant30,
    outline = NeutralVariant50
)

// Egg colors

val RawYolk = Color(255, 185, 57)
val BoiledYolk = Color(248, 133, 41)
val SoftYolk = Color(253, 153, 40)
val HardYolk = Color(255, 213, 44)
