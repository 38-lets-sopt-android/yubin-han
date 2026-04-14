package com.example.letssopt.designsystem.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.letssopt.designsystem.style.TextFieldStyle
import com.example.letssopt.designsystem.style.WatchaTextFieldColor
import com.example.letssopt.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.designsystem.theme.WatchaTheme

@Composable
fun WatchaBasicTextField(
    textStyle: TextStyle,
    textFieldColor: WatchaTextFieldColor,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    enabled: Boolean = true,
    trailingContent: @Composable () -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }

        BasicTextField(
            modifier = modifier.fillMaxWidth(),
            value = value,
            textStyle = textStyle,
            onValueChange = onValueChange,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            enabled = enabled,
            visualTransformation = visualTransformation,
            interactionSource = interactionSource,
            singleLine = singleLine,
            decorationBox = { innerTextField ->
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        if (value.isEmpty()) {
                            Text(
                                text = placeholder,
                                style = textStyle,
                                color = textFieldColor.textColor,
                            )
                        }
                        innerTextField()
                    }
                    trailingContent()
                }
            }
        )
    }

@Preview(showBackground = false)
@Composable
private fun BasicTextFieldPreview() {
    LETSSOPTTheme {
        var text by remember { mutableStateOf("") }

        WatchaBasicTextField(
            placeholder = "이메일을 입력하세요",
            value = text,
            onValueChange = { text = it },
            textStyle = WatchaTheme.typography.cap.captionR14,
            textFieldColor = TextFieldStyle.DEFAULT.getTextFieldColor(),
        )
    }
}
