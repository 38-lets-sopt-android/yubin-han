package com.example.letssopt.presentation.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.R
import com.example.letssopt.core.designsystem.theme.WatchaTheme


@Composable
fun HomeTopActionBar(
    modifier: Modifier = Modifier,
    onMyPageClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 23.dp)
            .padding(end = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(
            space = 14.dp,
            alignment = Alignment.End,
        ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            ImageVector.vectorResource(R.drawable.ic_watch),
            null,
            tint = WatchaTheme.colors.textPrimary,
            modifier = Modifier.clickable(onClick = {}),
        )
        Icon(
            ImageVector.vectorResource(R.drawable.ic_noti),
            null,
            tint = WatchaTheme.colors.textPrimary,
            modifier = Modifier.clickable(onClick = { }),

            )
        Icon(
            ImageVector.vectorResource(R.drawable.ic_profile),
            null,
            tint = WatchaTheme.colors.textPrimary,
            modifier = Modifier.clickable(onClick = onMyPageClick),
        )
    }
}

@Preview
@Composable
private fun HomeTopActionBarPreview() {
    HomeTopActionBar(
        onMyPageClick = {},
    )
}
