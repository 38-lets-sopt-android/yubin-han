package com.example.letssopt.presentation.home.myprofile.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.core.designsystem.theme.WatchaTheme.colors
import com.example.letssopt.core.designsystem.theme.WatchaTheme.typography


@Composable
fun MyProfileItemSection(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(start = 3.dp)
    ) {
        Text(
            text = label,
            style = typography.headline.head3B16,
            color = colors.textPrimary,
        )

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = value,
            style = typography.cap.captionR14,
            color = colors.textPrimary,
        )
    }
}

@Preview
@Composable
private fun MyProfileItemSectionPreview() {
    LETSSOPTTheme {
        MyProfileItemSection(
            label = "아이디",
            value = "아이디아이디"
        )
    }
}
