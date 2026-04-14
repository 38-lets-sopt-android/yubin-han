package com.example.letssopt.designsystem.component


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.designsystem.style.ButtonStyle
import com.example.letssopt.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.designsystem.theme.WatchaTheme.typography

@Composable
fun WatchaAuthButton(
    buttonStyle: ButtonStyle,
    buttonText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    disabled: Boolean = true,
) {
    WatchaBasicButton(
        buttonText = buttonText,
        onClick = onClick,
        modifier = modifier,
        buttonColor = buttonStyle.getButtonColor(),
        textStyle = typography.headline.head3B16,
        contentPadding = PaddingValues(horizontal = 97.dp, vertical = 16.dp),
        shape = RoundedCornerShape(8.dp),
        disabled = disabled,
    )
}

@Preview
@Composable
private fun WatchaBasicButtonPreview(
    modifier: Modifier = Modifier,
) {
    LETSSOPTTheme {
        Column {
            WatchaAuthButton(
                buttonStyle = ButtonStyle.PRIMARY,
                buttonText = "로그인",
                onClick = {},
                modifier = modifier,
            )
            WatchaAuthButton(
                buttonStyle = ButtonStyle.DISABLED,
                buttonText = "로그인",
                onClick = {},
                modifier = modifier,
                disabled = false,
            )

        }


    }
}
