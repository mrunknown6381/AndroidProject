package com.example.travelbuddy.navigation
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.travelbuddy.screen.forgetpass
import com.example.travelbuddy.screen.homescreen
import com.example.travelbuddy.screen.loginscreen
import com.example.travelbuddy.screen.onboardingscreen
import com.example.travelbuddy.screen.registerscreen
import com.example.travelbuddy.screen.termsandcondition
import com.example.travelbuddy.screen.welcomescreen

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun navgraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = screens.startroute.route) {

        navigation(startDestination = screens.Screenonboarding.route,route = screens.startroute.route){
            composable(screens.Screenonboarding.route) {
                onboardingscreen(navController = navController)
            }
            composable(screens.Screenwelcome.route) {
                welcomescreen(navController = navController)
            }
        }

        navigation(startDestination = screens.Screenregister.route,route = screens.authroute.route) {
            composable(screens.Screenregister.route){
                registerscreen(navController = navController)
            }
            composable(screens.Screentac.route){
                termsandcondition(navController = navController)
            }
            composable(screens.Screenlogin.route){
                loginscreen(navController=navController)
            }
            composable(screens.Screenforget.route){
                forgetpass(navController = navController)
            }
        }

        navigation(startDestination = screens.Screenhome.route,route = screens.approute.route) {
            composable(screens.Screenhome.route) {
                homescreen(navController = navController)
            }
        }

    }
}