package com.example.travelbuddy.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.travelbuddy.appcomponents.textbuttonRL
import com.example.travelbuddy.data.loginviewmodel
import com.example.travelbuddy.navigation.screens


@Composable
    fun homescreen(navController: NavController,loginviewmodel: loginviewmodel  = viewModel()) {

            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                if(loginviewmodel.logout.value){
                    navController.navigate(screens.Screenlogin.route)
                }
                Text(modifier = Modifier,
                    text = "Logout",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.background,
                    style = MaterialTheme.typography.displayLarge
                )
                textbuttonRL(text = "Logout", onClick = {
                }, onbuttonclicked = {
                    loginviewmodel.logout()
                },isEnabled = true)
            }
        }

