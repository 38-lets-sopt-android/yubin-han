package com.example.letssopt.designsystem.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

object WatchaTheme {
    val colors: WatchaColors
        @Composable
        @ReadOnlyComposable
        get() = LocalWatchaColorsProvider.current

    val typography: WatchaTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalWatchaTypographyProvider.current
}

@Composable
fun ProvideWatchaColorsAndTypography(
    colors: WatchaColors,
    typography: WatchaTypography,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalWatchaColorsProvider provides colors,
        LocalWatchaTypographyProvider provides typography,
        content = content
    )
}

@Composable
fun LETSSOPTTheme(
    content: @Composable () -> Unit
) {
    ProvideWatchaColorsAndTypography(
        colors = defaultWatchaColors,
        typography = defaultWatchaTypography
    ) {
        val view = LocalView.current
        if (!view.isInEditMode) {
            // optional
            SideEffect {
                (view.context as Activity).window.run {
                    WindowCompat.getInsetsController(this, view).isAppearanceLightStatusBars = true
                }
            }
        }
        MaterialTheme(
            content = content
        )
    }
}
