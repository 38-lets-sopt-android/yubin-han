package com.example.letssopt.designsystem.style

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import com.example.letssopt.designsystem.theme.WatchaTheme.colors

enum class ButtonStyle {
    PRIMARY,
    DISABLED;

    @ReadOnlyComposable
    @Composable
    fun getButtonColor(): WatchaBtnColor = when (this) {
        PRIMARY -> WatchaBtnColor(
            backgroundColor = colors.primaryRed,
            textColor = colors.textPrimary,
        )

        DISABLED -> WatchaBtnColor(
            backgroundColor = colors.disabled,
            textColor =  colors.placeHolder,
            disabledBackgroundColor = colors.disabled,
            disabledTextColor = colors.placeHolder,
        )
    }
}

@Immutable
data class WatchaBtnColor(
    val backgroundColor: Color,
    val textColor: Color,
    val disabledBackgroundColor: Color = backgroundColor,
    val disabledTextColor: Color = textColor,
)
