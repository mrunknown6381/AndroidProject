package com.example.travelbuddy.navigation
//it contains all the screens of the project
sealed class screens(val route:String){
    object Screenonboarding : screens(route = "onboarding")
    object Screenwelcome : screens(route = "welcome")
    object Screenlogin : screens(route = "login")
    object Screenregister : screens(route = "register")
    object Screentac : screens(route = "terms")
    object Screenhome : screens(route = "homescreen")
    object startroute : screens(route = "start")
    object Screenforget : screens(route = "forget")
    object authroute : screens(route = "auth")
    object approute : screens(route = "app")
}