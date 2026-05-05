package com.example.letssopt.presentation.home.myprofile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.core.data.model.home.myprofile.UserProfile
import com.example.letssopt.core.designsystem.component.WatchaBasicButton
import com.example.letssopt.core.designsystem.style.ButtonStyle
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.core.designsystem.theme.WatchaTheme.colors
import com.example.letssopt.core.designsystem.theme.WatchaTheme.typography
import com.example.letssopt.core.util.HandleUiEffects
import com.example.letssopt.presentation.home.myprofile.component.MyProfileItemSection

@Composable
fun MyProfileRoute(
    paddingValues: PaddingValues,
    navigateToUserList: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MyProfileViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HandleUiEffects(viewModel.effect) { effect ->
        when (effect) {
            is MyProfileContract.Effect.NavigateToUserList -> navigateToUserList()
        }
    }

    MyProfileScreen(
        uiState = uiState,
        onNavigateToUserList = viewModel::navigateToUserList,
        modifier = modifier.padding(paddingValues),
    )
}

@Composable
fun MyProfileScreen(
    uiState: MyProfileContract.UiState,
    onNavigateToUserList: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(color = colors.backGround)
            .fillMaxSize()
            .padding(horizontal = 20.dp),
    ) {
        Text(
            text = "프로필",
            style = typography.headline.head2B20,
            color = colors.textPrimary,
            modifier = Modifier.padding(top = 70.dp, start = 7.dp)
        )

        Spacer(modifier = Modifier.height(68.dp))

        MyProfileItemSection(
            label = "아이디",
            value = uiState.myProfile?.id ?: "",
        )
        Spacer(modifier = Modifier.height(30.dp))

        MyProfileItemSection(
            label = "이름",
            value = uiState.myProfile?.name ?: "",
        )

        Spacer(modifier = Modifier.height(30.dp))

        MyProfileItemSection(
            label = "이메일",
            value = uiState.myProfile?.email ?: "",
        )

        Spacer(modifier = Modifier.height(30.dp))

        MyProfileItemSection(
            label = "나이",
            value = uiState.myProfile?.age?.toString() ?: "",
        )

        Spacer(modifier = Modifier.height(30.dp))

        MyProfileItemSection(
            label = "파트",
            value = uiState.myProfile?.part ?: "",
        )

        Spacer(modifier = Modifier.height(30.dp))

        WatchaBasicButton(
            buttonText = "다른 유저들 보러가기",
            onClick = onNavigateToUserList,
            modifier = Modifier.fillMaxWidth(),
            buttonColor = ButtonStyle.PRIMARY.getButtonColor(),
            textStyle = typography.headline.head3B16,
            contentPadding = PaddingValues(horizontal = 90.dp, vertical = 16.dp),
            shape = RoundedCornerShape(8.dp),
            disabled = false,
        )
    }
}

@Preview
@Composable
private fun MyProfileScreenPreview() {
    LETSSOPTTheme {
        MyProfileScreen(
            uiState = MyProfileContract.UiState(
                myProfile = UserProfile(
                    id = "아이디아이디",
                    name = "어쩌고저쩌고",
                    email = "watcha@example.com",
                    age = 20,
                    part = "안드로이드"
                )
            ),
            onNavigateToUserList = {}
        )
    }
}
