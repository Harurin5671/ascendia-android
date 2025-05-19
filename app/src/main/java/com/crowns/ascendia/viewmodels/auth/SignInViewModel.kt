package com.crowns.ascendia.viewmodels.auth

import androidx.lifecycle.ViewModel
import com.crowns.ascendia.R
import com.crowns.ascendia.domain.validation.auth.AuthValidationError
import com.crowns.ascendia.domain.validation.auth.AuthValidator
import com.crowns.ascendia.util.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class SignInUiState(
    val email: String = "",
    val password: String = "",
    val isSignInEnabled: Boolean = false,
    val signInError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
)

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider
) : ViewModel() {
    private val _uiState = MutableStateFlow(SignInUiState())
    val uiState: StateFlow<SignInUiState> = _uiState.asStateFlow()

    fun onEmailChange(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun validate(): Boolean {
        val emailError = AuthValidator.validateEmail(_uiState.value.email)
        val passwordError = AuthValidator.validatePassword(_uiState.value.password)

        val isValid = emailError == null && passwordError == null

        _uiState.value = _uiState.value.copy(
            emailError = emailError?.let { getErrorMessage(it) },
            passwordError = passwordError?.let { getErrorMessage(it) },
            isSignInEnabled = isValid
        )

        return isValid
    }

    fun onSignInClick(): Boolean {
        return validate()
    }

    private fun getErrorMessage(error: AuthValidationError): String {
        return when (error) {
            AuthValidationError.EMPTY_EMAIL -> resourceProvider.getString(R.string.auth_email_empty)
            AuthValidationError.INVALID_EMAIL -> resourceProvider.getString(R.string.auth_invalid_email)
            AuthValidationError.EMPTY_PASSWORD -> resourceProvider.getString(R.string.auth_password_empty)
            AuthValidationError.PASSWORD_TOO_SHORT -> resourceProvider.getString(R.string.auth_password_too_short)
            AuthValidationError.PASSWORD_FORMAT_INVALID -> resourceProvider.getString(R.string.auth_password_format_invalid)
        }
    }
}