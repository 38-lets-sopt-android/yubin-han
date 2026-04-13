package com.example.letssopt.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Patterns.EMAIL_ADDRESS
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
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

enum class ValidationErrorType(val errorMessage: String) {
    INVALID_FORMAT("회원가입 형식에 맞지 않습니다."),
    PASSWORD_MISMATCH("비밀번호가 일치하지 않습니다.");
}

private fun getValidationError(
    emailText: String,
    pwText: String,
    pwConfirmText: String
): ValidationErrorType? {
    if (!EMAIL_ADDRESS.matcher(emailText).matches() || pwText.length !in 8..12) {
        return ValidationErrorType.INVALID_FORMAT
    }

    if (pwText != pwConfirmText) {
        return ValidationErrorType.PASSWORD_MISMATCH
    }

    return null
}


@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
) {
    var emailText by remember { mutableStateOf("") }
    var pwText by remember { mutableStateOf("") }
    var pwConfirmText by remember { mutableStateOf("") }

    val context = LocalContext.current

    val isAllEntered = emailText.isNotBlank() && pwText.isNotBlank() && pwConfirmText.isNotBlank()
    Column(
        modifier = modifier
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
                placeholder = "이메일을 입력하세요. (ex. abc@naver.com)",
                value = emailText,
                onValueChange = {
                    emailText = it
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email
                ),
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
                placeholder = "비밀번호를 입력하세요 (8~12자)",
                value = pwText,
                onValueChange = {
                    pwText = it
                },
                trailingContent = {},
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next,
                )
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
                value = pwConfirmText,
                onValueChange = {
                    pwConfirmText = it
                },
                trailingContent = {},
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                )
            )
        }

        WatchaBasicButton(
            buttonText = "회원가입",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(bottom = 26.dp),
            onClick = {
                val errorType = getValidationError(emailText, pwText, pwConfirmText)

                if (errorType != null) {
                    Toast.makeText(context, errorType.errorMessage, Toast.LENGTH_SHORT).show()
                } else {
                    val intent = Intent(context, LoginActivity::class.java).apply {
                        putExtra("email", emailText)
                        putExtra("password", pwText)
                    }
                    Toast.makeText(context, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                    val activity = context as? Activity
                    activity?.setResult(Activity.RESULT_OK, intent)
                    activity?.finish()
                }
            },
            disabled = !isAllEntered,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpPreview() {
    LETSSOPTTheme {
        SignUpScreen()
    }
}

