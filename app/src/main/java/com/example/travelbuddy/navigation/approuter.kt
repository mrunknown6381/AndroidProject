package com.example.travelbuddy.navigation

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.compose.rememberNavController
import com.example.travelbuddy.screen.loginscreen
import com.example.travelbuddy.screen.registerscreen

sealed class scr(){
    object regscreen : scr()
    object logscreen : scr()
}

object approuter {
    val currentscreen: MutableState<scr> = mutableStateOf(scr.regscreen)

    fun navigateto(dest: scr) {
        currentscreen.value = dest
    }
}
@Composable
fun ui(){
    Crossfade(targetState = approuter.currentscreen, label = "") { currentstate->
        when(currentstate.value){
            is scr.regscreen ->{
                registerscreen(navController = rememberNavController())
            }
            is scr.logscreen ->{
                loginscreen(navController = rememberNavController())
            }
        }
    }
}