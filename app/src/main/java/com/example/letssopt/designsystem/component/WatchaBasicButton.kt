package com.example.letssopt.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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

@Composable
fun WatchaBasicButton(
    buttonText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(
                color = WatchaTheme.colors.primaryRed,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 97.dp, vertical = 16.dp)
            .clickable(
                onClick = onClick
            ),
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
        WatchaBasicButton(
            buttonText = "로그인",
            onClick = {},
            modifier = modifier,
        )
    }
}
