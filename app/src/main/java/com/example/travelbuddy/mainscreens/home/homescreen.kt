package com.example.travelbuddy.mainscreens.home

import android.annotation.SuppressLint
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.travelbuddy.mainscreens.dataviewmodel


@SuppressLint("SuspiciousIndentation")
@Composable
    fun homescreen(navController: NavHostController,dataviewmodel: dataviewmodel = viewModel()) {
       var getdata = dataviewmodel.state.value
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){

                Text(modifier = Modifier,
                    text = getdata.username,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.background,
                    style = MaterialTheme.typography.displayLarge
                )
                Text(modifier = Modifier,
                    text = getdata.email,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.background,
                    style = MaterialTheme.typography.displayLarge
                )
                Text(modifier = Modifier,
                    text = getdata.bio,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.background,
                    style = MaterialTheme.typography.displayLarge
                )
                Text(modifier = Modifier,
                    text = getdata.user_id,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.background,
                    style = MaterialTheme.typography.displayLarge
                )


            }
        }

