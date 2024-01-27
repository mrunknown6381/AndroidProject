package com.example.travelbuddy.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.travelbuddy.R
import com.example.travelbuddy.navigation.screens
import com.example.travelbuddy.appcomponents.textbuttonwlcm
import com.example.travelbuddy.data.dimens
import androidx.compose.ui.tooling.preview.Preview as Preview1

@Preview1
@Composable
fun welcomescreen(navController: NavController) {

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.wlcm),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxHeight()
        )
        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.height(120.dp))
            Text(text = "Welcome To Travel Buddy", textAlign = TextAlign.Center, modifier = Modifier.padding(horizontal = dimens.MP1), style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = Color(0xFF6750A4))
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = " navigate the world,smarter your journey,our expertise. ", textAlign = TextAlign.Center, modifier = Modifier.padding(horizontal = dimens.MP1), style = MaterialTheme.typography.headlineSmall.copy(fontStyle = FontStyle.Italic),
                color = Color(0xFF6750A4))
            Spacer(modifier = Modifier.height(350.dp))
            textbuttonwlcm(text = "Register", onClick = {
                navController.navigate(screens.Screenregister.route)
            },isEnabled = true)
            Spacer(modifier = Modifier.height(30.dp))
            textbuttonwlcm(text = "Login", onClick = {
                navController.navigate(screens.Screenlogin.route)
            },isEnabled = true)

        }
    }

}