package com.crowns.ascendia.navigation

import androidx.navigation.NavController

interface AppNavigator {
    fun navigate(
        to: Any,
        popUpTo: Any? = null,
        inclusive: Boolean = true,
        clearBackStack: Boolean = false
    )

    fun goBack()
}

class DefaultAppNavigator(
    private val navController: NavController
) : AppNavigator {
    override fun navigate(
        to: Any,
        popUpTo: Any?,
        inclusive: Boolean,
        clearBackStack: Boolean
    ) {
        navController.navigate(to) {
            when {
                clearBackStack -> {
                    popUpTo(0)
                }

                popUpTo != null -> {
                    popUpTo(popUpTo) { this.inclusive = inclusive }
                }

                else -> {}
            }
        }
    }

    override fun goBack() {
        navController.popBackStack()
    }
}