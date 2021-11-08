package com.jine.timeggz.core_style.themes

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun TimeggzTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorPalette,
        typography = Typography,
        content = content
    )
}
