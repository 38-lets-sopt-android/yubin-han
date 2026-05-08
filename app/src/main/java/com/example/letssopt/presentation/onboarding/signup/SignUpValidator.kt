package com.example.letssopt.presentation.onboarding.signup

import android.util.Patterns

object SignUpValidator {
    fun validateSignUp(state: SignUpContract.UiState): String? {
        return when {
            state.idText.length !in 4..20 -> "아이디는 4~20자 사이여야 합니다."

            state.pwText.length !in 8..20 -> "비밀번호는 8~20자 사이여야 합니다."

            state.pwText != state.pwConfirmText -> "비밀번호가 일치하지 않습니다."

            state.nameText.length > 10 -> "이름은 10자 이하여야 합니다."

            !Patterns.EMAIL_ADDRESS.matcher(state.emailText).matches() -> "이메일 형식이 올바르지 않습니다."

            (state.ageText.toIntOrNull() ?: 0) !in 1..150 -> "나이는 1에서 150 사이여야 합니다."

            state.partText !in listOf("안드로이드", "iOS", "웹") -> "유효하지 않은 파트입니다. (안드로이드, iOS, 웹 중 하나를 입력해주세요.)"

            else -> null
        }
    }
}
