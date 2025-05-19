package com.crowns.ascendia.domain.validation.auth

object AuthValidator {
    fun validateEmail(email: String): AuthValidationError? {
        if (email.isBlank()) {
            return AuthValidationError.EMPTY_EMAIL
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return AuthValidationError.INVALID_EMAIL
        }
        return null
    }

    fun validatePassword(password: String): AuthValidationError? {
        if (password.isBlank()) {
            return AuthValidationError.EMPTY_PASSWORD
        }
        val regex = Regex("""^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%^&*]).{8,}$""")

        if(password.length < 8) {
            return AuthValidationError.PASSWORD_TOO_SHORT
        }

        return if (!regex.matches(password)) {
            AuthValidationError.PASSWORD_FORMAT_INVALID
        } else null
    }
}