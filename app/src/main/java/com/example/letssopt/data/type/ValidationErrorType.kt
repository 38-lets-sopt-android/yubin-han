package com.example.letssopt.data.type

enum class ValidationErrorType(val errorMessage: String) {
    INVALID_FORMAT("회원가입 형식에 맞지 않습니다."),
    PASSWORD_MISMATCH("비밀번호가 일치하지 않습니다.");
}
