package com.example.letssopt.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.designsystem.style.TextFieldStyle
import com.example.letssopt.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.designsystem.theme.WatchaTheme

@Composable
fun WatchaAuthTextField(
    textFieldStyle: TextFieldStyle,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation= VisualTransformation.None,
    enabled: Boolean = true,
) {
    Box(
        modifier = modifier
            .background(
                color = textFieldStyle.getTextFieldColor().backgroundColor,
                shape = RoundedCornerShape(8.dp),
            )
    ) {
        WatchaBasicTextField(
            placeholder = placeholder,
            value = value,
            onValueChange = onValueChange,
            textStyle = WatchaTheme.typography.cap.captionR14,
            textFieldColor = textFieldStyle.getTextFieldColor(),
            disabled = !enabled,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 17.dp),
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
        )
    }
}

@Preview(showBackground = false)
@Composable
private fun BasicTextFieldPreview() {
    LETSSOPTTheme {
        var text by remember { mutableStateOf("") }
        WatchaAuthTextField(
            textFieldStyle = TextFieldStyle.INPUT,
            placeholder = "이메일을 입력하세요",
            value = text,
            onValueChange = { text = it },
            modifier = Modifier,
        )
    }
}
