package com.crowns.ascendia.navigation.auth

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.crowns.ascendia.ui.screens.auth.ForgotPasswordScreen
import com.crowns.ascendia.ui.screens.auth.SignInScreen
import com.crowns.ascendia.ui.screens.auth.SignUpScreen

@Composable
fun AuthNavigator(rootNavigator: NavHostController) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = SignIn) {
        composable<SignIn> { SignInScreen() }
        composable<SignUp> { SignUpScreen() }
        composable<ForgotPassword> { ForgotPasswordScreen() }
    }
}