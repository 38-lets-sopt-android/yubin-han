package com.example.letssopt.presentation.onboarding.signup

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.letssopt.core.designsystem.component.WatchaAuthButton
import com.example.letssopt.core.designsystem.component.WatchaAuthTextField
import com.example.letssopt.core.designsystem.style.ButtonStyle
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.core.designsystem.theme.WatchaTheme
import com.example.letssopt.core.util.HandleUiEffects

@Composable
fun SignUpRoute(
    viewModel: SignUpViewModel,
    navigateToLogin: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HandleUiEffects(viewModel.effect) { effect ->
        when (effect) {
            is SignUpContract.Effect.ShowToast -> {
                Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
            }

            is SignUpContract.Effect.NavigateToNext -> {
                navigateToLogin()
            }
        }
    }

    SignUpScreen(
        uiState = uiState,
        onIdChange = viewModel::updateId,
        onPwChange = viewModel::updatePw,
        onPwConfirmChange = viewModel::updatePwConfirm,
        onNameChange = viewModel::updateName,
        onEmailChange = viewModel::updateEmail,
        onAgeChange = viewModel::updateAge,
        onPartChange = viewModel::updatePart,
        onSignUpClick = viewModel::signUp,
        modifier = modifier,
    )
}

@Composable
fun SignUpScreen(
    uiState: SignUpContract.UiState,
    onIdChange: (String) -> Unit,
    onPwChange: (String) -> Unit,
    onPwConfirmChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onAgeChange: (String) -> Unit,
    onPartChange: (String) -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .systemBarsPadding()
            .background(color = WatchaTheme.colors.backGround)
            .fillMaxSize()
            .imePadding()
            .padding(horizontal = 20.dp),
    ) {
        Text(
            text = "watcha",
            style = WatchaTheme.typography.logo.logoB36,
            color = WatchaTheme.colors.primaryRed,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 60.dp, bottom = 26.dp),
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
        ) {
            Text(
                text = "회원가입",
                style = WatchaTheme.typography.headline.head2B20,
                color = WatchaTheme.colors.textPrimary,
            )

            Spacer(modifier = Modifier.height(36.dp))

            WatchaAuthTextField(
                label = "아이디",
                value = uiState.idText,
                placeholder = "아이디를 입력하세요",
                onValueChange = onIdChange,
            )

            Spacer(modifier = Modifier.height(18.dp))

            WatchaAuthTextField(
                label = "비밀번호",
                value = uiState.pwText,
                placeholder = "비밀번호를 입력하세요 (8~12자)",
                onValueChange = onPwChange,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                )
            )

            Spacer(modifier = Modifier.height(18.dp))

            WatchaAuthTextField(
                label = "비밀번호 확인",
                value = uiState.pwConfirmText,
                placeholder = "비밀번호를 다시 입력하세요",
                onValueChange = onPwConfirmChange,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                )
            )
            Spacer(modifier = Modifier.height(18.dp))

            WatchaAuthTextField(
                label = "이름",
                value = uiState.nameText,
                placeholder = "이름을 입력하세요",
                onValueChange = onNameChange
            )

            Spacer(modifier = Modifier.height(18.dp))

            WatchaAuthTextField(
                label = "이메일",
                value = uiState.emailText,
                placeholder = "이메일을 입력하세요",
                onValueChange = onEmailChange,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                )
            )
            Spacer(modifier = Modifier.height(18.dp))

            WatchaAuthTextField(
                label = "나이",
                value = uiState.ageText,
                placeholder = "나이를 입력하세요",
                onValueChange = onAgeChange,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                )
            )
            Spacer(modifier = Modifier.height(18.dp))

            WatchaAuthTextField(
                label = "파트",
                value = uiState.partText,
                placeholder = "파트를 입력하세요 (안드로이드/iOS/웹)",
                onValueChange = onPartChange,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                )
            )
            Spacer(modifier = Modifier.height(30.dp))
        }

        WatchaAuthButton(
            buttonStyle = if (uiState.isAllEntered) ButtonStyle.PRIMARY else ButtonStyle.DISABLED,
            buttonText = "회원가입",
            onClick = onSignUpClick,
            disabled = !uiState.isAllEntered,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(bottom = 26.dp),
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun SignUpPreview() {
    LETSSOPTTheme {
        SignUpScreen(
            uiState = SignUpContract.UiState(),
            onIdChange = {},
            onPwChange = {},
            onPwConfirmChange = {},
            onNameChange = {},
            onEmailChange = {},
            onAgeChange = {},
            onPartChange = {},
            onSignUpClick = {},
        )
    }
}
