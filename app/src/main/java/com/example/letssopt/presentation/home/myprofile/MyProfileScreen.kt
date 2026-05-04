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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.core.designsystem.component.WatchaBasicButton
import com.example.letssopt.core.designsystem.style.ButtonStyle
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.core.designsystem.theme.WatchaTheme.colors
import com.example.letssopt.core.designsystem.theme.WatchaTheme.typography
import com.example.letssopt.presentation.home.myprofile.component.MyProfileItemSection


@Composable
fun MyProfileRoute(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    MyProfileScreen(
        modifier = modifier.padding(paddingValues),
        id = "ID",
        name = "이름",
        email = "email@example.com",
        age = "20",
        part = "Android"
    )
}

@Composable
fun MyProfileScreen(
    id: String,
    name: String,
    email: String,
    age: String,
    part: String,
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
            value = id,
        )
        Spacer(modifier = Modifier.height(30.dp))

        MyProfileItemSection(
            label = "이름",
            value = name,
        )

        Spacer(modifier = Modifier.height(30.dp))

        MyProfileItemSection(
            label = "이메일",
            value = email,
        )

        Spacer(modifier = Modifier.height(30.dp))

        MyProfileItemSection(
            label = "나이",
            value = age,
        )

        Spacer(modifier = Modifier.height(30.dp))

        MyProfileItemSection(
            label = "파트",
            value = part,
        )

        Spacer(modifier = Modifier.height(30.dp))

        WatchaBasicButton(
            buttonText = "다른 유저들 보러가기",
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            buttonColor = ButtonStyle.PRIMARY.getButtonColor(),
            textStyle = typography.headline.head3B16,
            contentPadding = PaddingValues(horizontal = 90.dp, vertical = 16.dp),
            shape = RoundedCornerShape(8.dp),
        )
    }
}


@Preview
@Composable
private fun MyProfileScreenPreview() {
    LETSSOPTTheme {
        MyProfileScreen(
            id = "아이디아이디",
            name = "이름이름",
            email = "이메일이메일",
            age = "1234",
            part = "안드로이드"
        )
    }
}
