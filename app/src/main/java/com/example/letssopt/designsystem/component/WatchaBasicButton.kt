package com.example.letssopt.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.designsystem.theme.WatchaTheme
import com.example.letssopt.designsystem.theme.WatchaTheme.typography
import com.example.letssopt.extension.noRippleClickable

@Composable
fun WatchaBasicButton(
    buttonText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    disabled: Boolean = true,
) {
    Box(
        modifier = modifier
            .background(
                if (!disabled) WatchaTheme.colors.primaryRed else WatchaTheme.colors.disabled,
                shape = RoundedCornerShape(8.dp)
            )
            .noRippleClickable(
                isEnabled = !disabled,
                onClick = onClick
            )
            .padding(horizontal = 97.dp, vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = buttonText,
            style = typography.headline.head3B16,
            color = WatchaTheme.colors.textPrimary,
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
            )
            WatchaBasicButton(
                buttonText = "로그인",
                onClick = {},
                modifier = modifier,
                disabled = false,
            )
        }


    }
}
