package com.crowns.ascendia.ui.screens.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.crowns.ascendia.ui.components.appbar.AppTopBar

@Composable
fun SignUpScreen() {
    Scaffold(topBar = {
        AppTopBar(
            title = "",
            showBackButton = true,
            modifier = Modifier
        )
    }) { it -> Column(modifier = Modifier.padding(it)) { Text(text = "Sign Up") } }
}