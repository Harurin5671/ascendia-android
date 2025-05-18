package com.crowns.ascendia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.crowns.ascendia.navigation.RootNavigator
import com.crowns.ascendia.ui.theme.AscendiaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AscendiaTheme {
                RootNavigator()
            }
        }
    }
}