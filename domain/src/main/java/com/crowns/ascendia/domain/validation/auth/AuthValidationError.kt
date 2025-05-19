package com.crowns.ascendia.domain.validation.auth

enum class AuthValidationError {
    EMPTY_EMAIL,
    INVALID_EMAIL,
    EMPTY_PASSWORD,
    PASSWORD_TOO_SHORT,
    PASSWORD_FORMAT_INVALID
}