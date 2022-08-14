package com.rinatvasilev.httpclient.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColors(
    background = colorLight,
    surface = colorLight
)

private val DarkColorPalette = darkColors(
    background = colorDark,
    surface = colorDark
)


@Composable
fun HttpClientTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
