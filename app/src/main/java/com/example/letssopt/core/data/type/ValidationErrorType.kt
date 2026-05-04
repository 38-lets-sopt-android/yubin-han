package com.example.letssopt.core.data.type

enum class ValidationErrorType(val errorMessage: String) {
    EMAIL_INVALID_FORMAT("이메일 형식에 맞지 않습니다."),
    PW_INVALID_FORMAT("비밀번호는 8~12자 사이로 입력해주세요"),

    PASSWORD_MISMATCH("비밀번호가 일치하지 않습니다."),

    EMPTY_INPUT("모든 정보가 입력되지 않았습니다"),
    AGE_INVALID_FORMAT("나이는 1세~150세 사이여야 합니다."),
}
