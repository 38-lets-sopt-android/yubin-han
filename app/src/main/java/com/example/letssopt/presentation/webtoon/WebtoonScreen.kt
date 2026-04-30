package com.example.letssopt.presentation.webtoon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.letssopt.core.designsystem.theme.WatchaTheme

@Composable
fun WebtoonRoute(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    WebtoonScreen(
        paddingValues = paddingValues,
        modifier = modifier
    )
}

@Composable
fun WebtoonScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(color = WatchaTheme.colors.backGround)
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "웹툰",
            color= WatchaTheme.colors.textPrimary,
            )
    }
}
