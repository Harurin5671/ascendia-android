package com.crowns.ascendia.viewmodels.auth

import androidx.lifecycle.ViewModel
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

class SignInViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(SignInUiState())
    val uiState: StateFlow<SignInUiState> = _uiState.asStateFlow()

    fun onEmailChange(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun validate(): Boolean {
        print("Validando")
        var isValid = true
        var emailError: String? = null
        var passwordError: String? = null

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(_uiState.value.email).matches()) {
            emailError = "Correo inválido"
            isValid = false
        }

        if (_uiState.value.password.length < 8) {
            passwordError = "Mínimo 6 caracteres"
            isValid = false
        }

        _uiState.value = _uiState.value.copy(
            emailError = emailError,
            passwordError = passwordError,
            isSignInEnabled = isValid
        )

        return isValid
    }

    fun onSignInClick() {
        if(validate()) {}
    }
}