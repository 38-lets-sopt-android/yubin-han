package com.example.letssopt.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


private val PrimaryRed = Color(0xFFE8003C)

private val BackGround = Color(0xFF141414)
private val Surface = Color(0xFF2A2A2A)

private val TextPrimary = Color(0xFFFFFFFF)
private val TextSecondary = Color(0xFF999999)
private val PlaceHolder = Color(0xFF666666)
private val Disabled = Color(0xFF333333)

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
