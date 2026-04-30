package com.example.letssopt.core.util

import com.example.letssopt.core.data.type.ValidationErrorType

object AuthValidator {
    private val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()

    fun validateSignUp(
        email: String,
        pw: String,
        pwConfirm: String
    ): ValidationErrorType? {
        val emailMatcher = email.matches(emailRegex)
        if (!emailMatcher || pw.length !in 8..12) {
            return ValidationErrorType.INVALID_FORMAT
        }
        if (pw != pwConfirm) {
            return ValidationErrorType.PASSWORD_MISMATCH
        }
        return null
    }
}
