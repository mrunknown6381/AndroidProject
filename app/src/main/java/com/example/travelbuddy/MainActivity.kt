package com.example.travelbuddy

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.example.travelbuddy.navigation.navgraphex
import com.example.travelbuddy.ui.theme.TravelBuddyTheme


class MainActivity : ComponentActivity() {


    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen()
        setContent {
            TravelBuddyTheme{
                // A surface container using the 'background' color from the theme
                Surface {
                       val navController = rememberNavController()
                        navgraphex(navController = navController)
                }
            }
        }
    }
}


