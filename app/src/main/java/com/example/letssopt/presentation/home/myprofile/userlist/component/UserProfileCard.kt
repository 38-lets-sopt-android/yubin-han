package com.example.letssopt.presentation.home.myprofile.userlist.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.core.designsystem.theme.WatchaTheme.colors
import com.example.letssopt.core.designsystem.theme.WatchaTheme.typography

@Composable
fun UserProfileCard(
    id: String,
    name: String,
    part: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = colors.textPrimary)
            .padding(start = 68.dp, top = 10.dp, bottom = 10.dp, end = 34.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            Text(
                text = id,
                style = typography.headline.head3B16,
                color = colors.textPrimary,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = name,
                style = typography.cap.captionR14,
                color = colors.textSecondary,
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = part,
            style = typography.cap.captionR14,
            color = colors.textPrimary,
        )
    }
}

@Preview
@Composable
private fun UserProfileCardPreview(
) {
    LETSSOPTTheme {
        UserProfileCard(
            id = "아이디",
            name = "나 누구게",
            part = "안드로이드"
        )
    }
}
