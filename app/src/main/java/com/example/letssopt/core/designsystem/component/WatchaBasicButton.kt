package com.example.letssopt.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.core.designsystem.style.ButtonStyle
import com.example.letssopt.core.designsystem.style.WatchaBtnColor
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.core.designsystem.theme.WatchaTheme.typography
import com.example.letssopt.core.extension.noRippleClickable


@Composable
fun WatchaBasicButton(
    buttonText: String,
    buttonColor: WatchaBtnColor,
    onClick: () -> Unit,
    textStyle: TextStyle,
    contentPadding: PaddingValues,
    shape: Shape,
    modifier: Modifier = Modifier,
    disabled: Boolean = true,
) {
    val (backgroundColor, contentColor) =
        if (!disabled) buttonColor.backgroundColor to buttonColor.textColor
        else buttonColor.disabledBackgroundColor to buttonColor.disabledTextColor

    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = shape,
            )
            .noRippleClickable(
                isEnabled = !disabled,
                onClick = onClick
            )
            .padding(contentPadding),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = buttonText,
            style = textStyle,
            color = contentColor,
        )
    }
}


@Preview
@Composable
private fun WatchaBasicButtonPreview(
    modifier: Modifier = Modifier,
) {
    LETSSOPTTheme {
        Column {
            WatchaBasicButton(
                buttonText = "로그인",
                onClick = {},
                modifier = modifier,
                buttonColor = ButtonStyle.PRIMARY.getButtonColor(),
                textStyle = typography.headline.head3B16,
                contentPadding = PaddingValues(horizontal = 97.dp, vertical = 16.dp),
                shape = RoundedCornerShape(8.dp),
            )
            Spacer(modifier = Modifier.height(16.dp))
            WatchaBasicButton(
                buttonText = "로그인",
                onClick = {},
                modifier = modifier,
                buttonColor = ButtonStyle.DISABLED.getButtonColor(),
                textStyle = typography.headline.head3B16,
                contentPadding = PaddingValues(horizontal = 97.dp, vertical = 16.dp),
                shape = RoundedCornerShape(8.dp),
                disabled = false,
            )
        }


    }
}
