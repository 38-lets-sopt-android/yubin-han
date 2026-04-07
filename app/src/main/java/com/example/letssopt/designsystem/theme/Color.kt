package com.example.letssopt.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


val PrimaryRed = Color(0xFFE8003C)

val BackGround = Color(0xFF141414)
val Surface = Color(0xFF2A2A2A)

val TextPrimary = Color(0xFFFFFFFF)
val TextSecondary = Color(0xFF999999)
val PlaceHolder = Color(0xFF666666)
val Disabled = Color(0xFF333333)

@Immutable
data class WatchaColors(
    val primaryRed: Color = PrimaryRed,
    val backGround: Color = BackGround,
    val surface: Color = Surface,
    val textPrimary: Color = TextPrimary,
    val textSecondary: Color = TextSecondary,
    val placeHolder: Color = PlaceHolder,
    val disabled: Color = Disabled,
)


val defaultWatchaColors = WatchaColors(
    primaryRed = PrimaryRed,
    backGround = BackGround,
    surface = Surface,
    textPrimary = TextPrimary,
    textSecondary = TextSecondary,
    placeHolder = PlaceHolder,
    disabled = Disabled,

    )

val LocalWatchaColorsProvider = staticCompositionLocalOf { defaultWatchaColors }
