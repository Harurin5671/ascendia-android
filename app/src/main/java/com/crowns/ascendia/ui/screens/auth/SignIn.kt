package com.crowns.ascendia.ui.screens.auth

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.crowns.ascendia.R
import com.crowns.ascendia.navigation.AppNavigator
import com.crowns.ascendia.navigation.Auth
import com.crowns.ascendia.navigation.ForgotPassword
import com.crowns.ascendia.navigation.Home
import com.crowns.ascendia.navigation.SignUp
import com.crowns.ascendia.ui.components.appbar.AppTopBar
import com.crowns.ascendia.ui.components.inputs.BaseTextField
import com.crowns.ascendia.ui.components.inputs.PasswordTextField
import com.crowns.ascendia.viewmodels.auth.SignInViewModel

@Composable
fun SignInScreen(appNavigator: AppNavigator) {
    val viewModel: SignInViewModel = hiltViewModel()
//    val viewModel = remember { SignInViewModel() }
    val uiState by viewModel.uiState.collectAsState()
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    Scaffold { paddding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddding)
                .padding(16.dp)
                .imePadding()
                .verticalScroll(scrollState)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { focusManager.clearFocus() })
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                stringResource(R.string.auth_welcome_title),
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            BaseTextField(
                value = uiState.email,
                onValueChange = { viewModel.onEmailChange(it) },
                label = stringResource(R.string.auth_email_hint),
                placeholder = stringResource(R.string.auth_email_hint),
                modifier = Modifier.fillMaxWidth(),
                keyBoardType = KeyboardType.Email,
                isError = uiState.emailError != null,
                errorMessage = uiState.emailError,
            )

            Spacer(modifier = Modifier.height(20.dp))

            PasswordTextField(
                value = uiState.password,
                onValueChange = { viewModel.onPasswordChange(it) },
                label = stringResource(R.string.auth_password_hint),
                placeholder = stringResource(R.string.auth_password_hint),
                modifier = Modifier.fillMaxWidth(),
                isError = uiState.passwordError != null,
                errorMessage = uiState.passwordError
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(
                onClick = {
                    appNavigator.navigate(ForgotPassword)
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(stringResource(R.string.auth_forgot_password))
            }

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {
                    focusManager.clearFocus()
                    if (viewModel.onSignInClick()) {
                        appNavigator.navigate(
                            to = Home,
                            popUpTo = Auth,
                            inclusive = true
                        )
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    disabledContainerColor = Color(0xFFB88AE8)
                ),
                enabled = uiState.email != "" && uiState.password != "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.auth_signin_button),
                    fontSize = 16.sp,
                    color = Color.White
                )
            }

            TextButton(
                onClick = {
                    appNavigator.navigate(SignUp)
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(stringResource(R.string.auth_no_account_signup))
            }
        }
    }
}