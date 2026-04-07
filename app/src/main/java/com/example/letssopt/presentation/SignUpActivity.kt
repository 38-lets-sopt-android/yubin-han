package com.example.letssopt.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.designsystem.component.WatchaBasicButton
import com.example.letssopt.designsystem.component.WatchaBasicTextField
import com.example.letssopt.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.designsystem.theme.WatchaTheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LETSSOPTTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SignUpScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
) {
    var emailText by remember { mutableStateOf("") }
    var pwText by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .background(color = WatchaTheme.colors.backGround)
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = "watcha",
            style = WatchaTheme.typography.logo.logoB36,
            color = WatchaTheme.colors.primaryRed,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 60.dp)
        )
        Spacer(modifier = Modifier.height(26.dp))
        Text(
            text = "회원가입",
            style = WatchaTheme.typography.headline.head2B20,
            color = WatchaTheme.colors.textPrimary,
        )
        Spacer(modifier = Modifier.height(36.dp))
        Text(
            text = "이메일",
            style = WatchaTheme.typography.cap.captionR14,
            color = WatchaTheme.colors.textSecondary,
        )

        Spacer(modifier = Modifier.height(3.dp))

        WatchaBasicTextField(
            placeholder = "이메일을 입력하세요",
            value = emailText,
            onValueChange = {},
            trailingContent = {},
        )

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "비밀번호",
            style = WatchaTheme.typography.cap.captionR14,
            color = WatchaTheme.colors.textSecondary,
        )
        Spacer(modifier = Modifier.height(3.dp))


        WatchaBasicTextField(
            placeholder = "비밀번호를 입력하세요",
            value = pwText,
            onValueChange = {},
            trailingContent = {},
        )
        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "비밀번호 확인",
            style = WatchaTheme.typography.cap.captionR14,
            color = WatchaTheme.colors.textSecondary,
        )
        Spacer(modifier = Modifier.height(3.dp))


        WatchaBasicTextField(
            placeholder = "비밀번호를 다시 입력하세요",
            value = pwText,
            onValueChange = {},
            trailingContent = {},
        )

        Spacer(modifier = Modifier.weight(1f))

        WatchaBasicButton(
            buttonText = "회원가입",
            onClick = {},
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(26.dp))
    }


}

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    LETSSOPTTheme {
        SignUpScreen()
    }
}

