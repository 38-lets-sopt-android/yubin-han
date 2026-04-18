package com.example.letssopt.presentation.main.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.letssopt.R
import com.example.letssopt.designsystem.theme.WatchaTheme


@Composable
fun TopActionBar(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(end = 20.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            ImageVector.vectorResource(R.drawable.ic_watch),
            null,
            tint = WatchaTheme.colors.textPrimary
        )
        Spacer(modifier = Modifier.width(14.dp))
        Icon(
            ImageVector.vectorResource(R.drawable.ic_noti),
            null,
            tint = WatchaTheme.colors.textPrimary
        )
        Spacer(modifier = Modifier.width(14.dp))
        Icon(
            ImageVector.vectorResource(R.drawable.ic_profile),
            null,
            tint = WatchaTheme.colors.textPrimary
        )
    }
}
