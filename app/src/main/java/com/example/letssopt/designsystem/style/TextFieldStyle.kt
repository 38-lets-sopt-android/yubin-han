package com.example.letssopt.designsystem.style

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import com.example.letssopt.designsystem.theme.WatchaTheme.colors


enum class TextFieldStyle {
    INPUT,
    DISABLED;

    @ReadOnlyComposable
    @Composable
    fun getTextFieldColor(): WatchaTextFieldColor = when (this) {
        INPUT -> WatchaTextFieldColor(
            backgroundColor = colors.surface,
            textColor = colors.textPrimary,
        )

        DISABLED -> WatchaTextFieldColor(
            backgroundColor = colors.surface,
            textColor = colors.placeHolder,
            disabledBackgroundColor = colors.surface,
            disabledTextColor = colors.placeHolder,
        )
    }
}

@Immutable
data class WatchaTextFieldColor(
    val backgroundColor: Color,
    val textColor: Color,
    val disabledBackgroundColor: Color = backgroundColor,
    val disabledTextColor: Color = textColor,
)
