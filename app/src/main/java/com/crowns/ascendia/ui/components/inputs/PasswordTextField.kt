package com.crowns.ascendia.ui.components.inputs

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.crowns.ascendia.R

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    modifier: Modifier,
    isError: Boolean = false,
    errorMessage: String? = null,
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    val icon = if (isPasswordVisible) R.drawable.ic_visibility_off else R.drawable.ic_visibility

    BaseTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        placeholder = placeholder,
        modifier = modifier,
        keyBoardType = KeyboardType.Password,
        isPassword = true,
        isPasswordVisible = isPasswordVisible,
        trailingIcon = {
            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = if (isPasswordVisible) stringResource(R.string.hide_password) else stringResource(
                        R.string.show_password
                    ),
                    tint = if (isError) Color.Red else Color.Unspecified
                )
            }
        },
        isError = isError,
        errorMessage = errorMessage
    )
}