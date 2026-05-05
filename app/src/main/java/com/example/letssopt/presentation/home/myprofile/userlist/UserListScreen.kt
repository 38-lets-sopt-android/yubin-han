package com.example.letssopt.presentation.home.myprofile.userlist

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.letssopt.core.data.model.home.myprofile.UserProfile
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.core.designsystem.theme.WatchaTheme.colors
import com.example.letssopt.core.designsystem.theme.WatchaTheme.typography
import com.example.letssopt.core.util.HandleUiEffects
import com.example.letssopt.presentation.home.myprofile.userlist.component.UserProfileCard
import kotlinx.coroutines.flow.flowOf

@Composable
fun UserListRoute(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    viewModel: UserListViewModel = viewModel(),
) {
    val userItems = viewModel.userPagingData.collectAsLazyPagingItems()
    val context = LocalContext.current

    HandleUiEffects(viewModel.effect) { effect ->
        when (effect) {
            is UserListContract.Effect.ShowToast -> {
                Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    UserListScreen(
        userItems = userItems,
        paddingValues = paddingValues,
        modifier = modifier,
    )
}

@Composable
fun UserListScreen(
    userItems: LazyPagingItems<UserProfile>,
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

        Spacer(modifier = Modifier.height(142.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(
                count = userItems.itemCount,
                key = userItems.itemKey { it.id }
            ) { index ->
                userItems[index]?.let { user ->
                    UserProfileCard(
                        id = user.id,
                        name = user.name,
                        part = user.part
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UserListScreenPreview() {
    val mockUsers = listOf(
        UserProfile("아이디", "나누구게", "sopt@example.com", 24, "안드로이드"),
        UserProfile("안드짱", "이영희", "a@example.com", 23, "iOS"),
        UserProfile("두쫀쿠", "박민수", "b@example.com", 25, "Web")
    )
    val mockPagingData = flowOf(PagingData.from(mockUsers))

    LETSSOPTTheme {
        UserListScreen(
            userItems = mockPagingData.collectAsLazyPagingItems()
        )
    }
}
