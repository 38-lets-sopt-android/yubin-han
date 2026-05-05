package com.example.letssopt.presentation.home.myprofile.userlist

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.core.data.model.home.myprofile.UserProfile
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.core.designsystem.theme.WatchaTheme.colors
import com.example.letssopt.core.designsystem.theme.WatchaTheme.typography
import com.example.letssopt.core.util.HandleUiEffects
import com.example.letssopt.presentation.home.myprofile.userlist.component.UserProfileCard
import kotlinx.collections.immutable.persistentListOf

@Composable
fun UserListRoute(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    viewModel: UserListViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    HandleUiEffects(viewModel.effect) { effect ->
        when (effect) {
            is UserListContract.Effect.ShowToast -> {
                Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    UserListScreen(
        uiState = uiState,
        modifier = modifier,
        paddingValues = paddingValues,
    )
}

@Composable
fun UserListScreen(
    uiState: UserListContract.UiState,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(),
) {
    Column(
        modifier = modifier
            .background(color = colors.backGround)
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Text(
            text = "친구들",
            style = typography.headline.head2B20,
            color = colors.textPrimary,
            modifier = Modifier.padding(top = 70.dp, start = 27.dp)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 142.dp),
        ) {
            items(
                items = uiState.users,
                key = { user -> user.id }
            ) { user ->
                UserProfileCard(
                    user.id,
                    user.name,
                    user.part
                )
            }
        }
    }
}

@Preview
@Composable
private fun UserListScreenPreview() {
    LETSSOPTTheme {
        UserListScreen(
            uiState = UserListContract.UiState(
                users = persistentListOf(
                    UserProfile(
                        id = "아이디",
                        name = "나 누구게",
                        email = "sopt@example.com",
                        age = 24,
                        part = "iOS"
                    ),
                    UserProfile(
                        id = "안드쫀쿠",
                        name = "나 누구게",
                        email = "ios@example.com",
                        age = 23,
                        part = "안드로이드"
                    ),
                )
            )
        )
    }
}
