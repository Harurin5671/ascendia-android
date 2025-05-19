package com.crowns.ascendia.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.crowns.ascendia.ui.screens.auth.ForgotPasswordScreen
import com.crowns.ascendia.ui.screens.auth.SignInScreen
import com.crowns.ascendia.ui.screens.auth.SignUpScreen
import com.crowns.ascendia.ui.screens.home.HomeScreen

@Composable
fun RootNavigator() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Auth) {
        navigation<Auth>(startDestination = SignIn) {
            composable<SignIn> { SignInScreen(navController) }
            composable<SignUp> { SignUpScreen() }
            composable<ForgotPassword> { ForgotPasswordScreen() }
        }
        composable<Home> { HomeScreen(navController) }
    }
}