package com.example.travelbuddy.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.travelbuddy.appcomponents.textbuttonwlcm


@Composable
    fun homescreen(navController: NavHostController) {

            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){

                Text(modifier = Modifier,
                    text = "Homescreen",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.background,
                    style = MaterialTheme.typography.displayLarge
                )
                textbuttonwlcm(text = "Login", onClick = {
                }, onbuttonclicked = {

                })

            }
        }

