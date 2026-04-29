package com.example.letssopt.presentation.onboarding.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.core.designsystem.component.WatchaAuthButton
import com.example.letssopt.core.designsystem.component.WatchaAuthTextField
import com.example.letssopt.core.designsystem.style.ButtonStyle
import com.example.letssopt.core.designsystem.style.TextFieldStyle
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.core.designsystem.theme.WatchaTheme
import com.example.letssopt.core.extension.noRippleClickable
import kotlinx.coroutines.flow.collectLatest


@Composable
fun LoginRoute(
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit,
    viewModel: LoginViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val uiState = viewModel.uiState

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is LoginContract.Effect.ShowToast -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }

                is LoginContract.Effect.NavigateToMain -> {
                    navigateToHome()
                }
            }
        }
    }

    LoginScreen(
        modifier = modifier,
        emailText = uiState.emailText,
        pwText = uiState.pwText,
        isLogInEnabled = uiState.isLogInEnabled,
        onEmailChange = viewModel::updateEmail,
        onPwChange = viewModel::updatePw,
        onLoginClick = { viewModel.login() },
        onSignUpClick = navigateToSignUp
    )

}

@Composable
fun LoginScreen(
    emailText: String,
    pwText: String,
    isLogInEnabled: Boolean,
    onEmailChange: (String) -> Unit,
    onPwChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .systemBarsPadding()
            .background(color = WatchaTheme.colors.backGround)
            .fillMaxSize()
            .imePadding()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = "watcha",
            style = WatchaTheme.typography.logo.logoB36,
            color = WatchaTheme.colors.primaryRed,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 60.dp, bottom = 26.dp)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "이메일로 로그인",
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

            WatchaAuthTextField(
                textFieldStyle = if (emailText.isNotBlank()) TextFieldStyle.INPUT else TextFieldStyle.DISABLED,
                placeholder = "이메일을 입력하세요",
                value = emailText,
                onValueChange = onEmailChange,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email
                ),
            )

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "비밀번호",
                style = WatchaTheme.typography.cap.captionR14,
                color = WatchaTheme.colors.textSecondary,
            )

            Spacer(modifier = Modifier.height(3.dp))

            WatchaAuthTextField(
                textFieldStyle = if (pwText.isNotBlank()) TextFieldStyle.INPUT else TextFieldStyle.DISABLED,
                placeholder = "비밀번호를 입력하세요",
                value = pwText,
                onValueChange = onPwChange,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                )
            )
        }
        Text(
            text = "아직 계정이 없으신가요?  회원가입",
            style = WatchaTheme.typography.cap.captionR14,
            color = WatchaTheme.colors.textSecondary,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .noRippleClickable(onClick = onSignUpClick)
        )
        Spacer(modifier = Modifier.height(20.dp))

        WatchaAuthButton(
            buttonStyle = if (isLogInEnabled) ButtonStyle.PRIMARY else ButtonStyle.DISABLED,
            buttonText = "로그인",
            onClick = onLoginClick,
            disabled = !isLogInEnabled,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(bottom = 26.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginPreview() {
    LETSSOPTTheme {
        LoginScreen(
            emailText = "",
            pwText = "",
            isLogInEnabled = false,
            onEmailChange = {},
            onPwChange = {},
            onLoginClick = {},
            onSignUpClick = {}
        )
    }
}
