package com.example.letssopt.designsystem.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.letssopt.R


object InterFont {
    val Bold = FontFamily(Font(R.font.pretendard_bold))
    val Regular = FontFamily(Font(R.font.pretendard_regular))

}

private fun watchaTextStyle(
    fontFamily: FontFamily,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    lineHeight: TextUnit = 1.3.em,
    letterSpacing: TextUnit = (-1.5).sp
): TextStyle = TextStyle(
    fontFamily = fontFamily,
    fontWeight = fontWeight,
    fontSize = fontSize,
    lineHeight = lineHeight,
    letterSpacing = letterSpacing,
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)

sealed interface TypographyTokens {
    @Immutable
    data class Logo(
        val logoB36: TextStyle,
    )

    @Immutable
    data class Headline(
        val head1B24: TextStyle,
        val head2B20: TextStyle,
        val head3B16: TextStyle,
    )

    @Immutable
    data class Body(
        val bodyR16: TextStyle,
    )

    @Immutable
    data class Caption(
        val captionR14: TextStyle,
    )
}

@Immutable
data class WatchaTypography(
    val logo: TypographyTokens.Logo,
    val headline: TypographyTokens.Headline,
    val body: TypographyTokens.Body,
    val cap: TypographyTokens.Caption,
)

val defaultWatchaTypography = WatchaTypography(
    logo = TypographyTokens.Logo(
        logoB36 = watchaTextStyle(
            fontFamily = InterFont.Bold,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        ),
    ),
    headline = TypographyTokens.Headline(
        head1B24 = watchaTextStyle(
            fontFamily = InterFont.Bold,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        ),
        head2B20 = watchaTextStyle(
            fontFamily = InterFont.Bold,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        ),
        head3B16 = watchaTextStyle(
            fontFamily = InterFont.Bold,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        ),
    ),
    body = TypographyTokens.Body(
        bodyR16 = watchaTextStyle(
            fontFamily = InterFont.Regular,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        ),
    ),
    cap = TypographyTokens.Caption(
        captionR14 = watchaTextStyle(
            fontFamily = InterFont.Regular,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal
        ),
    )
)

val LocalWatchaTypographyProvider = staticCompositionLocalOf { defaultWatchaTypography }


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun WatchaTypographyPreview() {
    val typography = defaultWatchaTypography

    Column(modifier = Modifier.padding(24.dp)) {
        Text(
            text = "Headline 1 (head1B24) - 24sp Bold",
            style = typography.headline.head1B24
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Headline 2 (head2B20) - 20sp Bold",
            style = typography.headline.head2B20
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Headline 3 (head3B16) - 16sp Bold",
            style = typography.headline.head3B16
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Body (bodyR16) - 16sp Regular",
            style = typography.body.bodyR16
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Caption (captionR14) - 14sp Regular",
            style = typography.cap.captionR14
        )
    }
}
