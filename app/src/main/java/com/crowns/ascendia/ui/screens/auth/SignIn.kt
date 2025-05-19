package com.crowns.ascendia.ui.screens.auth

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.crowns.ascendia.R
import com.crowns.ascendia.navigation.SignUp

@Composable
fun SignInScreen(navController: NavController) {
    val focusManager = LocalFocusManager.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold { paddding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddding)
                .padding(16.dp)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { focusManager.clearFocus() })
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = stringResource(R.string.auth_email_hint)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(stringResource(R.string.auth_password_hint)) },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    // Aquí llamas a tu lógica de login (ej: viewModel.login(email, password))
                    focusManager.clearFocus() // opcional
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.auth_signin_button))
            }

            TextButton(
                onClick = {
                    navController.navigate(SignUp)
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(stringResource(R.string.auth_no_account_signup))
            }
        }
    }
}