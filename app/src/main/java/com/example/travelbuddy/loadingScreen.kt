package com.example.travelbuddy

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.travelbuddy.appcomponents.loadanim
import com.example.travelbuddy.navigation.routes
import com.google.firebase.auth.FirebaseAuth

@Composable
fun loadingScreen(navController: NavHostController) {
    val firebaseuser = FirebaseAuth.getInstance().currentUser
    LaunchedEffect(firebaseuser){
        if(firebaseuser!= null){
            navController.navigate(routes.bottombar.routes)
        }
        else{
            navController.navigate(routes.Screenonboarding.routes)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                if (isSystemInDarkTheme()) Color(0xFF000000) else Color(0xFFFFFFFF)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            loadanim()
        }
    }
}