package com.crowns.ascendia.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.crowns.ascendia.ui.screens.home.HomeScreen

@Composable
fun RootNavigator() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Home) {
        composable<Home> { HomeScreen(navController) }
    }
}