package com.example.letssopt.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
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
import com.example.letssopt.extension.noRippleClickable

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LETSSOPTTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
) {
    var emailText by remember { mutableStateOf("") }
    var pwText by remember { mutableStateOf("") }

    var registeredEmail by remember { mutableStateOf("") }
    var registeredPw by remember { mutableStateOf("") }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                val email = data?.getStringExtra("email") ?: ""
                val pw = data?.getStringExtra("password") ?: ""
                registeredEmail = email
                registeredPw = pw
            }
        }
    val isLogInEnabled = emailText.isNotEmpty() && pwText.isNotEmpty()

    val context = LocalContext.current

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

            WatchaBasicTextField(
                placeholder = "이메일을 입력하세요",
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
                placeholder = "비밀번호를 입력하세요",
                value = pwText,
                onValueChange = {
                    pwText = it
                },
                trailingContent = {},
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
                .align(
                    Alignment.CenterHorizontally,
                )
                .noRippleClickable(
                    onClick = {
                        val intent = Intent(context, SignUpActivity::class.java)
                        launcher.launch(intent)
                    }
                )
        )
        Spacer(modifier = Modifier.height(20.dp))

        WatchaBasicButton(
            buttonText = "로그인",
            onClick = {
                if (emailText == registeredEmail && pwText == registeredPw) {
                    val intent = Intent(context, MainActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                    context.startActivity(intent)
                    Toast.makeText(context, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "이메일 또는 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                }
            },
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
        LoginScreen()
    }
}
