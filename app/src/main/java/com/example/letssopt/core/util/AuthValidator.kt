package com.example.letssopt.core.util

import com.example.letssopt.core.data.type.ValidationErrorType

object AuthValidator {
    private val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()

    fun validateSignUp(
        id: String,
        email: String,
        pw: String,
        pwConfirm: String,
        name: String,
        age: String,
        part: String
    ): ValidationErrorType? {
        if (id.isBlank() || email.isBlank() || pw.isBlank() || pwConfirm.isBlank() || name.isBlank() || age.isBlank() || part.isBlank()) {
            return ValidationErrorType.EMPTY_INPUT
        }
        if (pw != pwConfirm) {
            return ValidationErrorType.PASSWORD_MISMATCH
        }
        val emailMatcher = email.matches(emailRegex)
        if (!emailMatcher) {
            return ValidationErrorType.EMAIL_INVALID_FORMAT
        }
        if (pw.length !in 8..12) {
            return ValidationErrorType.PW_INVALID_FORMAT
        }
        if (age.toIntOrNull() !in 1..150) {
            return ValidationErrorType.AGE_INVALID_FORMAT
        }
        return null
    }
}
