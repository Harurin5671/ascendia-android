package com.crowns.ascendia.ui.components.inputs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun BaseTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    modifier: Modifier,
    keyBoardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
    isPasswordVisible: Boolean? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    errorMessage: String? = null,
) {
    if (isPassword) {
        require(isPasswordVisible != null) {
            "If isPassword is true, passwordVisible must not be null."
        }
        require(trailingIcon != null) {
            "If isPassword is true, trailingIcon must not be null."
        }
    }

    Column {
        OutlinedTextField(
            value = value,
            textStyle = TextStyle(color = if (isError) Color.Red else Color.Unspecified),
            onValueChange = { onValueChange(it) },
            label = { Text(label, color = if (isError) Color.Red else Color.Unspecified) },
            placeholder = {
                Text(
                    placeholder,
                    color = if (isError) Color.Red else Color.Unspecified
                )
            },
            singleLine = true,
            modifier = modifier,
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = if (isError) Color.Red else Color(0xFF000000),
                unfocusedBorderColor = if (isError) Color.Red else Color(0xFFD8DADC),
                errorBorderColor = Color.Red,
                errorTrailingIconColor = Color.Red,
                errorTextColor = Color.Red,
                errorLabelColor = Color.Red,
                errorPlaceholderColor = Color.Red
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyBoardType
            ),
            visualTransformation = if (isPassword && isPasswordVisible == false) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = trailingIcon
        )

        if (isError && !errorMessage.isNullOrBlank()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
        }
    }
}