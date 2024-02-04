package com.example.travelbuddy.features.guide.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.travelbuddy.appcomponents.textbuttonwlcm
import com.example.travelbuddy.data.dimens
import com.example.travelbuddy.navigation.separate.routes

@Composable
fun welcomescreen(navController: NavController) {

    val g7 = Brush.verticalGradient(
        0.0f to Color(0xFF764BA2),
        1.0f to Color(0XFF667EEA),
        startY = 0.0f,
        endY = 3000.0f
    )
    Box(
        modifier = Modifier.fillMaxSize().background(brush = g7), contentAlignment = Alignment.Center
    ) {
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.height(120.dp))
            Text(text = "Welcome To Travel Buddy", textAlign = TextAlign.Center, modifier = Modifier.padding(horizontal = dimens.MP1), style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = Color.Black)
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = " navigate the world,smarter your journey,our expertise. ", textAlign = TextAlign.Center, modifier = Modifier.padding(horizontal = dimens.MP1), style = MaterialTheme.typography.headlineSmall.copy(fontStyle = FontStyle.Italic),
                color = Color.Black)
            Spacer(modifier = Modifier.height(350.dp))
            textbuttonwlcm(text = "Register", onClick = {
            }, onbuttonclicked = {
                navController.navigate(routes.Screenregister.routes)
            })
            Spacer(modifier = Modifier.height(30.dp))
            textbuttonwlcm(text = "Login", onClick = {
            }, onbuttonclicked = {
                navController.navigate(routes.Screenlogin.routes)
            })
        }
    }

}