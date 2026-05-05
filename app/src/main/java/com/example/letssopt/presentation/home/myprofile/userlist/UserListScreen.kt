package com.example.letssopt.presentation.home.myprofile.userlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.core.designsystem.theme.WatchaTheme.colors


@Composable
fun UserListRoute(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    UserListScreen(
        modifier = modifier,
        paddingValues = paddingValues,
    )
}

@Composable
fun UserListScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(),
) {
    Column(
        modifier = modifier
            .background(color = colors.backGround)
            .fillMaxSize()
            .padding(horizontal = 20.dp),
    ) {
        Text(
            text = "유저 리스트 화면",
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Preview
@Composable
private fun UserListScreenPreview() {
    LETSSOPTTheme {
        UserListScreen()
    }

}
